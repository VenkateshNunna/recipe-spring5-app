package com.example.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;

@Controller
public class RecipeController {
	
	private RecipeRepository recipeRepository;
	
	public RecipeController(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}



	@RequestMapping(value= {"","/","/index"})
	public String getRecipe() {
		Iterator<Recipe> recipes = recipeRepository.findAll().iterator();
		
		while(recipes.hasNext())
		{
			System.out.println(recipes.next().getDirections());
		}
		
		return "index";
	}
	
}
