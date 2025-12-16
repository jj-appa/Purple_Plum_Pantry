package com.plumPantry.recipes.ingredients;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientsRepository extends MongoRepository<Ingredient,String>
{     Ingredient findByIngName(String ingName);
}

