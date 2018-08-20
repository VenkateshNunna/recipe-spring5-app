package com.example.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.commands.IngredientCommand;
import com.example.commands.UnitOfMeasureCommand;
import com.example.convert.IngredientToIngredientCommand;
import com.example.convert.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.model.Ingredient;
import com.example.model.Recipe;
import com.example.repositories.IngredientRepository;
import com.example.repositories.RecipeRepository;

public class IngredientServiceImplTest {
	
	IngredientServiceImpl ingredientServiceImpl;
	
	@Mock
	RecipeRepository recipeRepository;
	
	
	
	IngredientToIngredientCommand ingredientToIngredientCommand;
	
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		ingredientServiceImpl = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,null,null);
		
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		ingredient1 = new Ingredient();
		ingredient1.setId(2L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		ingredient1 = new Ingredient();
		ingredient1.setId(3L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);
		
		IngredientCommand resultIngredient = ingredientServiceImpl.findByRecipeIdAndIngredientId(1L, 2L);
		
		assertEquals(Long.valueOf(1L), resultIngredient.getRecipeId());
		assertEquals(Long.valueOf(2L), resultIngredient.getId());
		verify(recipeRepository,times(1)).findById(ArgumentMatchers.anyLong());
	}
	
	@Test
	public void testDeleteByRecipeIdAndIngredientId() {
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		ingredient1 = new Ingredient();
		ingredient1.setId(2L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		ingredient1 = new Ingredient();
		ingredient1.setId(3L);
		ingredient1.setRecipe(recipe);
		recipe.getIngredients().add(ingredient1);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);

		ingredientServiceImpl.deleteByRecipeIdAndIngredientId(1L,2L);
		
		
		verify(recipeRepository,times(1)).findById(ArgumentMatchers.anyLong());
		verify(recipeRepository,times(1)).save(ArgumentMatchers.any());
		
	}

}
