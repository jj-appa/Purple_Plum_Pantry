package com.plumPantry.recipes.users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;



@Document(collection = "users")
public class Users {
    private ObjectId id;
    private String email;
    private String password;
    private String username;
    private String userCred;
    private List<Integer> recipesCreated;
    private Integer userId;
    private List<Integer> favoriteRecipes;



    public Users(ObjectId id, String email, String password, String username, Integer userId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userCred = username + password;
        this.recipesCreated = new ArrayList<Integer>();
        this.userId = userId;
        this.favoriteRecipes = new ArrayList<Integer>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserCred(){
        return userCred;
    }
    public void  setUserCred(String username, String password){
        this.userCred = username+password;
    }
    public List<Integer> getRecipesCreated(){
        return this.recipesCreated;
    }
    public void setRecipesCreated(List<Integer> recipesCreated){
        this.recipesCreated = recipesCreated;
    }
    public Integer getUserId(){
        return this.userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public List<Integer> getFavoriteRecipes(){
        return this.favoriteRecipes;
    }
    public void setFavoriteRecipes(List<Integer> favoriteRecipes){
        this.favoriteRecipes = favoriteRecipes;
    }
}
