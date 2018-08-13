package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
