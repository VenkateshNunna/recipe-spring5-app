package com.example.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.commands.IngredientCommand;
import com.example.commands.RecipeCommand;
import com.example.services.IngredientService;
import com.example.services.RecipeService;

public class IngredientControllerTest {

	@InjectMocks
	IngredientController ingredientController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
		
	}

	@Test
	public void testListIngredients() throws Exception {
		
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findById(anyLong())).thenReturn(recipeCommand);
		
		mockMvc.perform(get("/recipe/2/ingredients"))
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(status().isOk());
				
	}
	
	@Test
	public void testShowIngredient() throws Exception{
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		when(ingredientService.findByRecipeIdAndIngredientId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
					.thenReturn(ingredientCommand);
		
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"));

	}

}
