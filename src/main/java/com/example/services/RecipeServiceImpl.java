package com.example.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	RecipeRepository recipeRepository;
	
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}



	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}



	@Override
	public Recipe findById(Long l) {
		recipeRepository.findAll();
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return recipeOptional.get();
	}

}
