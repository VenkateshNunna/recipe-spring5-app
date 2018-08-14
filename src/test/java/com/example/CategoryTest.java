package com.example;

import static org.junit.Assert.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import com.example.model.Category;

public class CategoryTest {

	Category category;
	
	
	@Test
	public void getId() {
		category = new Category();
		Long number = 4L;
		category.setId(number);
		assertEquals(number, category.getId());
	}
	
}
