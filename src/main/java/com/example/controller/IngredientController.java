package com.example.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.commands.IngredientCommand;
import com.example.commands.RecipeCommand;
import com.example.commands.UnitOfMeasureCommand;
import com.example.repositories.UnitOfMeasureRepository;
import com.example.services.IngredientService;
import com.example.services.RecipeService;
import com.example.services.UnitOfMeasureService;

@Controller
public class IngredientController {

	
	RecipeService recipeService;
	
	IngredientService ingredientService;
	
	UnitOfMeasureService unitOfMeasureService;
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}


	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable String id, Model model) {
		
		RecipeCommand recipeCommand = recipeService.findById(new Long(id));
		
		model.addAttribute("recipe", recipeCommand);
		
		return "recipe/ingredient/list";
		
	}
	
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		model.addAttribute("ingredient", ingredientCommand);
		return "recipe/ingredient/show";
	}
	
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model)
	{
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		Set<UnitOfMeasureCommand> listOfUoms = unitOfMeasureService.lisAllUOM();
		
		model.addAttribute("ingredient", ingredientCommand);
		model.addAttribute("uomList", listOfUoms);
		
		return "recipe/ingredient/ingredientform";
		
		
	}
	
	@PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
		System.out.println("Command details are "+command.getDescription()+command.getAmount()+command.getId()+command.getUnitOfMeasure());
		System.out.println(command.getUnitOfMeasure().getId());
        IngredientCommand savedCommand = ingredientService.saveIngredient(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
	
	
	@RequestMapping("/recipe/{recipeId}/ingredient/new")
	public String addNewIngredient(@PathVariable String recipeId, Model model)
	{
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
		Set<UnitOfMeasureCommand> listOfUoms = unitOfMeasureService.lisAllUOM();
		
		model.addAttribute("ingredient", ingredientCommand);
		model.addAttribute("uomList", listOfUoms);
		
		return "recipe/ingredient/ingredientform";
		
	}
	
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
	public String deleteByRecipeIdAndIngredientId(@PathVariable String recipeId, @PathVariable String ingredientId) {
		System.out.println("recipe id is "+recipeId);
		System.out.println("Ingredient id is "+ingredientId);
		ingredientService.deleteByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}
	
	
}
