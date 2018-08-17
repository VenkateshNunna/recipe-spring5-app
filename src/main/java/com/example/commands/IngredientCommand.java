package com.example.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

	private Long id;
	private Long recipeId;
    private String description;
    private Float amount;
    private UnitOfMeasureCommand unitOfMeasure;
	
}
