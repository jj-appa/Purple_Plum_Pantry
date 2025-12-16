package com.plumPantry.recipes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RecipesRepository extends MongoRepository<Recipes, ObjectId> {
    Recipes findByRecipeId(Integer recipeId);
}
