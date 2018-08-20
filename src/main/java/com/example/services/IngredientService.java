package com.example.services;

import com.example.commands.IngredientCommand;

public interface IngredientService {

	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);

	IngredientCommand saveIngredient(IngredientCommand ingredientCommand);
	
	void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
}
