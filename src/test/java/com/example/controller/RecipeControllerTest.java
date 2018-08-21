package com.example.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.example.commands.RecipeCommand;
import com.example.exceptions.NotFoundException;
import com.example.model.Recipe;
import com.example.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	@InjectMocks
	RecipeController recipeController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
					.setControllerAdvice(new ControllerExceptionHandler())
					.build();
	}

	@Test
	public void getRecipeById() throws Exception {
		
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(1L);
		
		when(recipeService.findById(1L)).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/show"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/show"));
		
		
	}
	
	@Test
	public void testGetRecipeByIdNotFound() throws Exception {
		
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/recipe/1/show"))
			.andExpect(status().isNotFound())
			.andExpect(view().name("404error"))
			.andExpect(model().attributeExists("exception"));
	}
	
	@Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show/"));
    }
	
	@Test
	public void testUpdateRecipe() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);
		
		when(recipeService.findById(anyLong())).thenReturn(recipeCommand);
		
		mockMvc.perform(get("/recipe/2/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("/recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testDeleteById() throws Exception {
		
		mockMvc.perform(get("/recipe/2/delete"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteById(anyLong());
		
	}
	

}
