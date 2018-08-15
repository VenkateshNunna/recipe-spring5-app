package com.example.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.commands.RecipeCommand;
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
	
	@RequestMapping("/recipe/{id}/show")
	public String getRecipeById(@PathVariable String id,Model model) {
		RecipeCommand recipe = recipeService.findById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "recipe/show";
	}
	
	@RequestMapping("/recipe/new")
	public String newForm(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "/recipe/recipeform";
	}
	
	@RequestMapping("/recipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/"+savedRecipeCommand.getId()+"/show/";
	}
	
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		RecipeCommand recipe = recipeService.findById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "/recipe/recipeform";
	}
	
	@RequestMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id)
	{
		recipeService.deleteById(new Long(id));
		
		return "redirect:/";
	}
	
}
