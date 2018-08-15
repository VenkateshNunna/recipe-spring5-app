package com.example.convert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.example.commands.CategoryCommand;
import com.example.model.Category;

public class CategoryCommandToCategoryTest {
	
	private final static Long LONG_ID = 1L;
	private static final String DESCRIPTION = "DESCRIPTION";

	@InjectMocks
	CategoryCommandToCategory converter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNull() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void  convertTest() {
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(LONG_ID);
		categoryCommand.setDescription(DESCRIPTION);
		
		Category category = converter.convert(categoryCommand);
		
		assertEquals("ID is Not Matched", LONG_ID, category.getId());
		assertEquals("Description is Not Matched", DESCRIPTION, category.getDescription());
	}

}
