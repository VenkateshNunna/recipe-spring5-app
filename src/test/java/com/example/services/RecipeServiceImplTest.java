package com.example.services;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	@Mock
	RecipeRepository recipeRepository;
	
	@InjectMocks
	RecipeServiceImpl recipeServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRecipeById() {
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		 when(recipeRepository.findById(1L)).thenReturn(recipeOptional);

		
		Recipe recipeReturned = recipeServiceImpl.findById(1L);
		
		assertNotNull("Null recipe Returned", recipeReturned);
		verify(recipeRepository, times(1)).findById(1L);
		verify(recipeRepository, never()).findAll();
		
		
	}

}
