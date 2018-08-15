package com.example.services;

import java.util.Set;

import com.example.commands.RecipeCommand;
import com.example.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	RecipeCommand findById(Long id);
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	void deleteById(Long id);
	
}
