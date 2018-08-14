package com.example.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;
import com.example.services.RecipeService;

@Controller
public class RecipeController {
	
	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}



	@RequestMapping(value= {"","/","/index"})
	public String getRecipe(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String getRecipeById(@PathVariable String id,Model model) {
		Recipe recipe = recipeService.findById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "recipe/show";
	}
	
}
