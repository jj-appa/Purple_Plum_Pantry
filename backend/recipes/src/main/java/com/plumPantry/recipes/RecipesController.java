package com.plumPantry.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plumPantry.recipes.Recipes.ingrMeas;
import com.plumPantry.recipes.Recipes.userRatings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/db/Recipes")
public class RecipesController {
    @Autowired
    private RecipesService recipesService;




    //Get localhost:8080/db/Recipes, will get a recipes in database
    @GetMapping
    public ResponseEntity<List<Recipes>> getAllRecipes(){
        return new ResponseEntity<List<Recipes>>(recipesService.allRecipes(), HttpStatus.OK);
    }
    
    //Get localhost:8080/db/Recipes/recipe/{recipeId}, will get a single recipe from recipeId
    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<Recipes> getSingleRecipeByID(@PathVariable Integer recipeId){
        return new ResponseEntity<Recipes>(recipesService.singleRecipeFromRID(recipeId), HttpStatus.OK);

    }
    //Delete localhost:8080/db/Recipes/delete/recipe/{recipeId}, will delete a recipe from recipeId
    @DeleteMapping("/delete/recipe/{recipeId}")
    public ResponseEntity<String> deleteRecipeByID(@PathVariable Integer recipeId){
        return new ResponseEntity<String>(recipesService.deleteSingleFromRID(recipeId),HttpStatus.OK);
    }
    
    //POST localhost:8080/db/Recipes,  will insert a recipe
    @SuppressWarnings("unchecked")
    @PostMapping
    public ResponseEntity<Recipes> insertRecipe(@RequestBody Map<String, Object> payload){
        String rt = (String) payload.get("recipeTitle");
        String img =(String) payload.get("image");
        List<String> instr =(List<String>) payload.get("instructions");
        List<String> ingr = (List<String>) payload.get("ingredients");
        Integer cookTime = (Integer) payload.get("cookTime");
        Double rate = (Double) payload.get("rating");
        String uN = (String) payload.get("username");
        Boolean iP = (Boolean) payload.get("isPublic");
        List<String> tg= (List<String>)payload.get("tags");
        List<ingrMeas> measDescr = (List<ingrMeas>) payload.get("measDescr");
        List<userRatings> ratingDict = new ArrayList<userRatings>();
        return new ResponseEntity<>(recipesService.insertRecipe(rt, img, instr, ingr, cookTime, rate, uN, iP, tg, measDescr, ratingDict), HttpStatus.OK);
    }


    //PUT localhost:8080/db/Recipes/update/{recipeId} but will update a recipe using recipeId
    @SuppressWarnings("unchecked")
    @PutMapping("update/{recipeId}")
    public ResponseEntity<Recipes> updateRecipe(@PathVariable Integer recipeId, @RequestBody Map<String, Object> payload) {
        String rt = (String) payload.get("recipeTitle");
        String img =(String) payload.get("image");
        List<String> instr =(List<String>) payload.get("instructions");
        List<String> ingr = (List<String>) payload.get("ingredients");
        Integer cookTime = (Integer) payload.get("cookTime");
        Double rate = (Double) payload.get("rating");
        String uN = (String) payload.get("username");
        Boolean iP = (Boolean) payload.get("isPublic");
        List<String> tg= (List<String>)payload.get("recipeTags");
        List<ingrMeas> measDescr = (List<ingrMeas>) payload.get("measDescr");
        return new ResponseEntity<Recipes>(recipesService.updateRecipe(recipeId, rt, img, instr, ingr, cookTime, rate, uN, iP, tg, measDescr), HttpStatus.OK);
    }

    //Put localhost:8080/db/Recipes/update/rating/{recipeId}/{userId}, will update a rating based on the user's userId and will add it to the dictionary and set the updated rating to a recipe
    @PutMapping("/update/rating/{recipeId}")
    public ResponseEntity<Recipes> updateRating(@PathVariable Integer recipeId, @RequestBody Map<String, Object> payload){
        
        
        Integer userId = (Integer) payload.get("userId");
        Number userR = (Number) payload.get("userR");
        return new ResponseEntity<Recipes>(recipesService.updateRating(recipeId, userId, userR.doubleValue()), HttpStatus.OK);
        
        

    }


}