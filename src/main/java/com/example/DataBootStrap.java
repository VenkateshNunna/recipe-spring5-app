package com.example;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.model.Category;
import com.example.model.Difficulty;
import com.example.model.Ingredient;
import com.example.model.Notes;
import com.example.model.Recipe;
import com.example.model.UnitOfMeasure;
import com.example.repositories.CategoryRepository;
import com.example.repositories.RecipeRepository;
import com.example.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataBootStrap implements ApplicationListener<ContextRefreshedEvent>{

	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	
	
	public DataBootStrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		log.debug("DataBootStrap constructor is called");
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		log.debug("Inside onApplicationEvent");
		initData();
		
	}
	
	public void initData() {
		
		log.debug("initData is called");
		Recipe guacamole = new Recipe();
		guacamole.setDescription("Perfect Guacamole");
		guacamole.setPrepTime(10);
		guacamole.setCookTime(10);
		guacamole.setDifficulty(Difficulty.MEDIUM);
		guacamole.setServings(4);
		guacamole.setSource("Simply Recipes");
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamole.setDirections("All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato." );
		
		Notes guacamoleNotes = new Notes();
		guacamoleNotes.setNotes("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. "
				+ "				 2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
				+ "				 3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste."
				+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n" + 
				"\r\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.	");
		
		guacamoleNotes.setRecipe(guacamole);
		
		guacamole.setNotes(guacamoleNotes);
		
		Set<Ingredient> ingredients = new HashSet<Ingredient>(); 
		UnitOfMeasure quantity = unitOfMeasureRepository.findByDescription("Quantity").get();
		UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon").get(); 
		UnitOfMeasure teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
		UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get(); 
		
		Ingredient ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("ripe avocados");
		ingredient.setUom(quantity);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(0.5F);
		ingredient.setDescription("Kosher salt");
		ingredient.setUom(teaSpoon);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("resh lime juice or lemon juice");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("minced red onion or thinly sliced green onion");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("serrano chiles, stems and seeds removed, minced");
		ingredient.setUom(quantity);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("cilantro (leaves and tender stems), finely chopped");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("A dash of freshly grated black pepper");
		ingredient.setUom(quantity);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(.5F);
		ingredient.setDescription("ripe tomato, seeds and pulp removed, chopped");
		ingredient.setUom(quantity);
		ingredient.setRecipe(guacamole);
		ingredients.add(ingredient);
		
		
		guacamole.setIngredients(ingredients);
		
		Category mexican = categoryRepository.findByDescription("Mexican").get();
		Category veg = categoryRepository.findByDescription("Veg").get();
		Category chicken = categoryRepository.findByDescription("Chicken").get();
		Category american = categoryRepository.findByDescription("American").get();
		
		guacamole.getCategories().add(veg);
		guacamole.getCategories().add(mexican);
		
		veg.getRecipies().add(guacamole);
		mexican.getRecipies().add(guacamole);
		
		recipeRepository.save(guacamole);
		
		Recipe grilledChicken = new Recipe();
		grilledChicken.setDescription("Spicy grilled chicken tacos!");
		grilledChicken.setPrepTime(20);
		grilledChicken.setCookTime(15);
		grilledChicken.setDifficulty(Difficulty.EASY);
		grilledChicken.setServings(6);
		grilledChicken.setDirections("The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat.");
		grilledChicken.setSource("Simply Recipe");
		grilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("ancho chili powder");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("dried oregano");
		ingredient.setUom(teaSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("dried cumin");
		ingredient.setUom(teaSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("sugar");
		ingredient.setUom(teaSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(.5F);
		ingredient.setDescription("salt");
		ingredient.setUom(teaSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("clove garlic, finely chopped");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("finely grated orange zest");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(3F);
		ingredient.setDescription("fresh-squeezed orange juice");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("olive oil");
		ingredient.setUom(tableSpoon);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(6F);
		ingredient.setDescription("skinless, boneless chicken thighs");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(8F);
		ingredient.setDescription("small corn tortillas");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(3F);
		ingredient.setDescription("packed baby arugula (3 ounces)");
		ingredient.setUom(cup);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(2F);
		ingredient.setDescription("medium ripe avocados, sliced");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(4F);
		ingredient.setDescription("radishes, thinly sliced");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(.5F);
		ingredient.setDescription("pint cherry tomatoes, halved");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(.5F);
		ingredient.setDescription("red onion, thinly sliced");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("Roughly chopped cilantro");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(.5F);
		ingredient.setDescription("sour cream thinned with 1/4 cup milk");
		ingredient.setUom(cup);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setAmount(1F);
		ingredient.setDescription("lime, cut into wedges");
		ingredient.setUom(quantity);
		ingredient.setRecipe(grilledChicken);
		grilledChicken.getIngredients().add(ingredient);
		
		Notes grilledChickenTacoNotes = new Notes();
		grilledChickenTacoNotes.setNotes("1 Prepare a gas or charcoal grill for medium-high, direct heat."
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over."
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings."
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes."
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n" + 
				"\r\n" + 
				"Wrap warmed tortillas in a tea towel to keep them warm until serving."
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		
		grilledChicken.setNotes(grilledChickenTacoNotes);
		grilledChickenTacoNotes.setRecipe(grilledChicken);
		
		grilledChicken.getCategories().add(chicken);
		grilledChicken.getCategories().add(american);
		chicken.getRecipies().add(grilledChicken);
		american.getRecipies().add(grilledChicken);
		
		recipeRepository.save(grilledChicken);
	}
	

}
