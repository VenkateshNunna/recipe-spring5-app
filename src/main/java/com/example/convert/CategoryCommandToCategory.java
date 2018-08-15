package com.example.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.commands.CategoryCommand;
import com.example.model.Category;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	@Override
	public Category convert(CategoryCommand categoryCommand) {
		if(categoryCommand == null)
			return null;
		
		Category category = new Category();
		category.setId(categoryCommand.getId());
		category.setDescription(categoryCommand.getDescription());
		
		return category;
	}

}
