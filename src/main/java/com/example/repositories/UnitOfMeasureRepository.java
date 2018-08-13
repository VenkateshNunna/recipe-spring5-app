package com.example.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
	Optional<UnitOfMeasure> findByDescription(String description);
}
