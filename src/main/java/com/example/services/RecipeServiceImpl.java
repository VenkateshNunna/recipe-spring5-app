package com.example.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.commands.RecipeCommand;
import com.example.convert.RecipeCommandToRecipe;
import com.example.convert.RecipeToRecipeCommand;
import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	RecipeRepository recipeRepository;
	RecipeCommandToRecipe recipeCommandToRecipe;
	RecipeToRecipeCommand recipeToRecipeCommand;
	

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}



	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}



	@Override
	public RecipeCommand findById(Long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipeOptional.get());
		return recipeCommand;
	}



	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(recipe);
		
		return recipeToRecipeCommand.convert(savedRecipe);
	}



	@Override
	public void deleteById(Long id) {
		
		recipeRepository.deleteById(id);
		
	}

}
