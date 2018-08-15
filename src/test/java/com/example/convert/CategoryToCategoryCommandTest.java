package com.example.convert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.example.commands.CategoryCommand;
import com.example.model.Category;

public class CategoryToCategoryCommandTest {

	private final static Long LONG_ID = 1L;
	private static final String DESCRIPTION = "DESCRIPTION";
	
	@InjectMocks
	CategoryToCategoryCommand convert;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testNull() {
		assertNull(convert.convert(null));
		
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(convert.convert(new Category()));
	}

	@Test
	public void Convert() {
		
		Category category = new Category();
		category.setId(LONG_ID);
		category.setDescription(DESCRIPTION);
		
		CategoryCommand categoryCommand = convert.convert(category);
		
		assertEquals("ID is Not Matched", LONG_ID, categoryCommand.getId());
		assertEquals("Description is Not Matched", DESCRIPTION, categoryCommand.getDescription());
		
	}

}
