package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

}
