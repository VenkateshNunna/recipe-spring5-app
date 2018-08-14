package com.example.services;

import java.util.Set;

import com.example.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	Recipe findById(Long id);
}
