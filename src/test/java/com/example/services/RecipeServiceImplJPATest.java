package com.example.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.commands.RecipeCommand;
import com.example.convert.RecipeToRecipeCommand;
import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplJPATest {

	private static final String NEW_DESCRIPTION = "This is new description";
	@Autowired
	RecipeService recipeService;
	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Test
	@Transactional
	public void testsaveRecipeCommand() {
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe recipe = recipes.iterator().next();
		
		RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
		
		recipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		
		assertEquals("Description is incorrect", NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(recipe.getId(), savedRecipeCommand.getId());
		assertEquals(recipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		
	}
	
}
