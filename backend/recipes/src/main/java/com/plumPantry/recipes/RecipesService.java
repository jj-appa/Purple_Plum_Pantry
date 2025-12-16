package com.plumPantry.recipes;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plumPantry.recipes.users.UsersRepository;
import com.plumPantry.recipes.Recipes.ingrMeas;
import com.plumPantry.recipes.Recipes.userRatings;
import com.plumPantry.recipes.users.Users;

import com.plumPantry.recipes.users.UsersService;


import java.util.List;
import java.util.Optional;

@Service
public class RecipesService {
    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;
    
    public List<Recipes> allRecipes(){
        return recipesRepository.findAll();
    }


    //Ignore
    public Optional<Recipes> singleRecipe(ObjectId id){
        return recipesRepository.findById(id);
    }

    public Recipes singleRecipeFromRID(Integer RID){
        return recipesRepository.findByRecipeId(RID);
    }


    //Ignore
    public String deleteSingle(ObjectId id) {
        recipesRepository.deleteById(id);
        return "Recipe was deleted";
    }



    public String deleteSingleFromRID(Integer RID){
        Recipes x =recipesRepository.findByRecipeId(RID); // This finds the recipe by RecipeId
        
        if(usersRepository.findByUsername(x.getUsername()) == null){//checks to see if a user actually exists in DB before trying to find it and gets it list of RecipeIds
            return deleteSingle(x.get_id());//if not then just delete it without having to find the user it was created by
        }
        Users y = usersRepository.findByUsername(x.getUsername()); // This finds the user by the username from the Recipe author/username and creates a coopy of that user
        List<Integer> i = y.getRecipesCreated(); //Make a copy of the current list of created recipes
        i.remove(i.indexOf(RID));// pop out the recipeId from the list of created recipes for that user
        if(i.isEmpty()){
            i.add(null);
        }


        y.setRecipesCreated(i); //Updates the copy with the new list of recipesIds;
        usersService.updateUser(y.getUserId(), y); //Pushes that to database




        
        return deleteSingle(x.get_id());//Finally deletes Recipe using the objectId from the recipe
    }

    public Recipes insertRecipe(String recipeTitle, String img, List<String> instr, List<String> ingr, Integer cook, Double rt, String userName,Boolean iP, List<String> tags, List<ingrMeas> measDescr, List<userRatings> ratingDict){
        Integer x = recipesRepository.findAll().getLast().getRecipeId();//This gets the last id in the collections
        Integer RID = x+1;// We just add one and that will be the new id for the new recipe
        Recipes recipes = recipesRepository.insert(new Recipes(recipeTitle, img, instr, ingr, cook, userName, iP, tags, RID, measDescr, ratingDict));

        if(usersRepository.findByUsername(userName) == null){
            return recipes;
        }
        Users y = usersRepository.findByUsername(recipes.getUsername());// Gets user info by username and makes a copy
        List<Integer> i = y.getRecipesCreated();// makes a copy of recipesCreated
        if(i.contains(null)){
            i.remove(i.indexOf(null));
        }
        i.addLast(RID); //adds a recipe id to the list
        y.setRecipesCreated(i);//Updates copy
        usersService.updateUser(y.getUserId(), y); //updates the user using the userId and inserts the modifed copy.

        return recipes;
    }

    public Recipes updateRecipe(Integer id, String recipeTitle, String img, List<String> instr, List<String> ingr, Integer cook, Double rt, String userName,Boolean iP, List<String> recipeTags, List<ingrMeas> measDescr) {
        
        Recipes x = recipesRepository.findByRecipeId(id);

        if(x != null){
            if(recipeTitle != null){
               x.setRecipeTitle(recipeTitle);
            }
            if(img != null){
                x.setImage(img);
            }
            if(instr != null){
                x.setInstructions(instr);;
            }
            if(ingr != null){
                x.setIngredients(ingr);;
            }
            if(cook != null){
                x.setCookTime(cook);;
            }
            if(userName != null){
                x.setUsername(userName);
            }
            if(iP != null){
                x.setIsPublic(iP);
            }
            if(recipeTags != null){
                x.setRecipeTags(recipeTags);
            }
            if(measDescr != null){
                x.setMeasDescr(measDescr);
            }


            return recipesRepository.save(x);
        }
        return x;
    }

    public Recipes updateRating(Integer recipeId, Integer userId, Double userR){



        Recipes x = recipesRepository.findByRecipeId(recipeId);
        List<userRatings> y= x.getRatingDict();


        boolean dec = false; 
        Integer num = 0;// Will be used to store index of element found

        for(int j =0; j<y.size(); j++){// Checks to see if the userId is alraady in the ratingsList
            if(y.get(j).getUserId().equals(userId)){ //if it is then it will confirm and save position of where
                dec = true;
                num = j;
            }

        }

        if(dec){
            if(userR.doubleValue() == 0.0){
                y.remove(num.intValue());
                x.setRatingDict(y);
            } else {

                y.get(num).setUserR(userR); // finds and updates element if true


            }
        } else {y.addLast(new userRatings(userId, userR)); }// otherwise add it to the list


        
        Double temp = (Double) 0.0;// temp rating variable 

        for(int i = 0; i<y.size(); i++){//Iterates through each element and adds all ratings together
            temp += y.get(i).getUserR();
        }

        Double newRating = temp/y.size(); //calculates average


        x.setRating(newRating); //set the rating
        return recipesRepository.save(x);//save it
    }

}
