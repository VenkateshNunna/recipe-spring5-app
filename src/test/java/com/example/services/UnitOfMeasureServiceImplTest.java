package com.example.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cglib.core.Converter;

import com.example.commands.UnitOfMeasureCommand;
import com.example.convert.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.model.UnitOfMeasure;
import com.example.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {

	UnitOfMeasureServiceImpl unitOfMeasureServiceImpl;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	UnitOfMeasureToUnitOfMeasureCommand converter = new UnitOfMeasureToUnitOfMeasureCommand();
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		unitOfMeasureServiceImpl = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, converter);
	}

	@Test
	public void testLisAllUOM() {
		
		Set<UnitOfMeasure> uom = new HashSet<>();
		
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setDescription("");
		uom1.setId(1L);
		uom.add(uom1);
		
		uom1 = new UnitOfMeasure();
		uom1.setDescription("");
		uom1.setId(2L);
		uom.add(uom1);
		
		when(unitOfMeasureRepository.findAll()).thenReturn(uom);
		
		Set<UnitOfMeasureCommand> listOfUoms = unitOfMeasureServiceImpl.lisAllUOM();
		
		assertEquals(2, listOfUoms.size());
		
		verify(unitOfMeasureRepository,times(1)).findAll();
		
	}

}
