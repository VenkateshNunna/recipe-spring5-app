package com.example.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.commands.UnitOfMeasureCommand;
import com.example.convert.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.model.UnitOfMeasure;
import com.example.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	UnitOfMeasureRepository unitOfMeasureRepository;
	
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand; 
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> lisAllUOM() {
		
		Set<UnitOfMeasureCommand> uoms = new HashSet<>(); 
				unitOfMeasureRepository.findAll()
										.forEach( uom -> uoms.add(unitOfMeasureToUnitOfMeasureCommand.convert(uom)));
				
		return uoms;
	}

}
