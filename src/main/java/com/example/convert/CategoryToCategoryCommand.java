package com.example.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.commands.CategoryCommand;
import com.example.model.Category;


@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{
	
	
	@Override
	public CategoryCommand convert(Category category) {
		
		if(category == null)
		{
			return null;
		}
		
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(category.getId());
		categoryCommand.setDescription(category.getDescription());
		
		return categoryCommand;
	}

}
