package com.plumPantry.recipes.ingredients;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    //GET, return all ingredients
    public List<Ingredient> allIngredients(){
        return ingredientsRepository.findAll();
    }

    //GET, return specific Ingredient by name
    public Optional<Ingredient> singleIngredient(String name){
        Ingredient ingredient = ingredientsRepository.findByIngName(name);
        return Optional.ofNullable(ingredient);
    }

    //DELETE, delete specific ingredient by name
    public String delete(String name) {
        Ingredient ingredient = ingredientsRepository.findByIngName(name);
        if (ingredient != null) {
            ingredientsRepository.delete(ingredient);
            return "Ingredient was deleted";
        } else {
            return "Ingredient: "+ name + " not found";
        }
    }
    //POST, creating new Ingredient (by json format request body)
    public Ingredient insert(Ingredient ingredient){
        return ingredientsRepository.insert(ingredient);
    }

    //PUT, update existing Ingredient (by database object id)
    public Ingredient update(ObjectId id, Ingredient newIngredients) {
        Optional<Ingredient> existingIngredientOptional = ingredientsRepository.findById(String.valueOf(id));
        if (existingIngredientOptional.isPresent()) {
            Ingredient existingIngredient = existingIngredientOptional.get();
            existingIngredient.setIngName(newIngredients.getIngName());
            return ingredientsRepository.save(existingIngredient);
        } else {
            return null;
        }
    }

}