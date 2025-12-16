package com.plumPantry.recipes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipes {
    @Id
    private ObjectId _id;
    private String recipeTitle;
    private String image;
    private Integer cookTime;
    private Double rating;
    private String username;
    private Boolean isPublic;
    private Integer recipeId;
    private List<String> recipeTags;
    private List<String> instructions;
    private List<String> ingredients;

    private List<ingrMeas> measDescr;
    private List<userRatings> ratingDict;


    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userRatings{
        private Integer userId;
        private Double userR;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ingrMeas {
        private String k;
        private String v;
    } 


    public Recipes( String recipeTitle, String image, List<String> instructions,
                    List<String> ingredients, Integer cookTime, String username,
                    Boolean isPublic, List<String> recipeTags, Integer recipeId,
                     List<ingrMeas> measDescr, List<userRatings> ratingDict) {
        this.recipeTitle = recipeTitle;
        this.image = image;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.cookTime = cookTime;
        this.rating = 0.0;
        this.username = username;
        this.isPublic = isPublic;
        this.recipeTags=recipeTags;
        this.recipeId = recipeId;
        this.measDescr = measDescr;
        this.ratingDict = ratingDict;
    }
    
    public String getRecipeTitle(){
        return recipeTitle;
    }
    public String getImage(){
        return image;
    }
    public List<String> getInstructions(){
        return instructions;
    }
    public List<String> getIngredients(){
        return ingredients;
    }
    public Integer getCookTime(){
        return cookTime;
    }
    public Double getRating(){
        return rating;
    }

    public String getUsername(){
        return username;
    }
    public Boolean getIsPublic(){
        return isPublic;
    }
    public List<String> getRecipeTags(){
        return recipeTags;
    }

    public Integer getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(Integer recipeId){
        this.recipeId = recipeId;
    }
}
