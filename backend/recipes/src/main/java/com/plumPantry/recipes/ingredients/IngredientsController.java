package com.plumPantry.recipes.ingredients;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/db/Ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsService ingredientsService;
    //Mapping

    // GET localhost:8080/db/Ingredients, Return all ingredients
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        return new ResponseEntity<>(ingredientsService.allIngredients(), HttpStatus.OK);
    }
    // GET localhost:8080/db/Ingredients/{name}, Return specific ingredient by name
    @GetMapping("/{name}")
    public ResponseEntity<Optional<Ingredient>> getIngredient(@PathVariable String name){
        return new ResponseEntity<>(ingredientsService.singleIngredient(name), HttpStatus.OK);
    }

    //DELETE localhost:8080/db/Ingredients/delete/{name-here}, delete by name
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable String name) {
        String result = ingredientsService.delete(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST localhost:8080/db/Ingredients, Creating new ingredient using json format in request
    @PostMapping
    public ResponseEntity<Ingredient> insertIngredient(@RequestBody Ingredient ingredient){
        return new ResponseEntity<>(ingredientsService.insert(ingredient), HttpStatus.OK);
    }
    // PUT http://localhost:8080/db/Ingredients/update/{id}, updating existing ingredient, need ingredient database id.
    @PutMapping("update/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable ObjectId id, @RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(ingredientsService.update(id, ingredient), HttpStatus.OK);
    }
}

