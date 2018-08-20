package com.example.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.commands.IngredientCommand;
import com.example.convert.IngredientCommandToIngredient;
import com.example.convert.IngredientToIngredientCommand;
import com.example.model.Ingredient;
import com.example.model.Recipe;
import com.example.repositories.IngredientRepository;
import com.example.repositories.RecipeRepository;
import com.example.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	RecipeRepository recipeRepository;
	IngredientToIngredientCommand converter;
	UnitOfMeasureRepository unitOfMeasureRepository;
	IngredientCommandToIngredient iCTIConverter;
	
	public IngredientServiceImpl(RecipeRepository recipeRepository,
									IngredientToIngredientCommand converter,
									UnitOfMeasureRepository unitOfMeasureRepository,
									IngredientCommandToIngredient iCTIConverter) {
		super();
		this.recipeRepository = recipeRepository;
		this.converter = converter;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.iCTIConverter = iCTIConverter;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		
		Optional<IngredientCommand> ingredientCommand;
		
		if(recipe.isPresent()) {
			System.out.println("Converter is "+converter);
			ingredientCommand = recipe.get().getIngredients().stream()
								.filter(ingredient -> ingredient.getId().equals(ingredientId))
								.map(ingredient -> converter.convert(ingredient)).findFirst();
			
		}
		else {
			throw new RuntimeException("Recipe is not found for given id");
		}
		
		return ingredientCommand.get();
	}
	
	@Override
	public IngredientCommand saveIngredient(IngredientCommand ingredientCommand)
	{
		Optional<Recipe> recipe = recipeRepository.findById(ingredientCommand.getRecipeId());
		
		if(!recipe.isPresent()) {
			throw new RuntimeException("Recipe is not found");
		}
		else {
			Recipe fetchedRecipe = recipe.get();
			Optional<Ingredient> OptionalMatchedIngredient = fetchedRecipe
												.getIngredients()
												.stream()
												.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
												.findFirst();
			
			if(OptionalMatchedIngredient.isPresent()) {
				Ingredient ingredientFound = OptionalMatchedIngredient.get();
				System.out.println("Found matched ingredient");
				
				ingredientFound.setAmount(ingredientCommand.getAmount());
				ingredientFound.setDescription(ingredientCommand.getDescription());
				ingredientFound.setRecipe(fetchedRecipe);
				System.out.println("ingredientCommand.getUnitOfMeasure() is "+ ingredientCommand.getUnitOfMeasure());
				ingredientFound.setUom(unitOfMeasureRepository
										.findById(ingredientCommand.getUnitOfMeasure().getId())
										.orElseThrow(()-> new RuntimeException("")));
			}
			else {
				Ingredient ingredient = iCTIConverter.convert(ingredientCommand);
				ingredient.setRecipe(fetchedRecipe);
				fetchedRecipe.getIngredients().add(ingredient);
			}
			
			Recipe savedRecipe = recipeRepository.save(fetchedRecipe);
			
			Optional<Ingredient> savedIngredient = savedRecipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();
			
			if(!savedIngredient.isPresent()) {
				System.out.println("Not matching the ingredient id");
				
				savedIngredient = savedRecipe.getIngredients()
							.stream()
							.filter( ingredient -> {
								System.out.println(ingredient.getDescription());
								System.out.println(ingredientCommand.getDescription());
								return ingredient.getDescription().equals(ingredientCommand.getDescription());
							})
							.filter(ingredient -> {
								System.out.println(ingredient.getAmount());
								System.out.println(ingredientCommand.getAmount());
								return ingredient.getAmount().equals(ingredientCommand.getAmount());
							})
							.filter(ingredient -> {
								System.out.println(ingredient.getUom().getId());
								System.out.println(ingredientCommand.getUnitOfMeasure().getId());
								return ingredient.getUom().getId().equals(ingredientCommand.getUnitOfMeasure().getId());
							}).findFirst();
			}
			
			return converter.convert(savedIngredient.get());
												
		}
		
	}

	@Override
	@Transactional
	public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(recipeOptional.isPresent()) {
			
			Recipe recipe = recipeOptional.get();
			System.out.println("Recipe is present");
			Optional<Ingredient> ingredientOptional = recipe.getIngredients()
										.stream()
										.filter(ingredient -> ingredient.getId().equals(ingredientId))
										.findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredient = ingredientOptional.get();
				System.out.println("Ingredient present");
				ingredient.setRecipe(null);
				recipe.getIngredients().remove(ingredient);
				recipeRepository.save(recipe);
				
				
			}
			else {
				throw new RuntimeException("Ingredient is not found");
			}
		}
		else {
			throw new RuntimeException("Recipe is not found");
		}
		
		
	}

}
