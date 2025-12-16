package com.plumPantry.recipes.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    //GET, return all users
    public List<Users> allUsers(){
        return usersRepository.findAll();
    }

    //GET, return specific User by username
    public Optional<Users> singleUser(String username){
        Users user = usersRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }
    //Get, check to see if token matches
    public Boolean checkUserCred(String username, String userCred) {

        if(singleUser(username).isPresent()){
            Users x = singleUser(username).get();
            
            if(x.getUserCred().equals(userCred)){
                return true;
            }
        } 
        else {
            return false;
        }
        return false;

    }

    //DELETE, delete specific user by username
    public String deleteUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            usersRepository.delete(user);
            return "User was deleted";
        } else {
            return "User with username " + username + " not found";
        }
    }
    public boolean checkUsernameDB(String username){ // This checks to see if a username is already taken
        if(usersRepository.findByUsername(username) == null) {
            return false;//Return false that it is not taken
        }
        return true;//Return true that it is taken

    }
    public boolean checkEmailDB(String email){ // This checks to see if a email is taken
        if(usersRepository.findByEmail(email) == null) {
            return false;
        }
        return true;//Return true that it doesnt exist

    }


    //POST, creating new User (by json format request body)
    public Users insertUser(Users user){
        Integer x = usersRepository.findAll().getLast().getUserId();
        user.setUserId(x+1);
        return usersRepository.insert(user);
    }

    //PUT, update existing User by username
    public Users updateUser(Integer userId, Users newUser) {
        Users existingUserOptional = usersRepository.findByUserId(userId);
        if (existingUserOptional != null) {
            Users existingUser = existingUserOptional;
            if (newUser.getEmail() != null) { existingUser.setEmail(newUser.getEmail()); };
            if (newUser.getUsername() != null) {existingUser.setUsername(newUser.getUsername()); };
            if (newUser.getPassword() != null) {existingUser.setPassword(newUser.getPassword()); };
            if (newUser.getRecipesCreated() !=null) {
                if(newUser.getRecipesCreated().isEmpty()){
                        existingUser.setRecipesCreated(existingUser.getRecipesCreated());
                }
                else {
                    existingUser.setRecipesCreated(newUser.getRecipesCreated());
                }
            }
            existingUser.setUserCred(existingUser.getUsername(), existingUser.getPassword());
            return usersRepository.save(existingUser);
        } else {
            return null;
        }
    }



    //PUT, updating a users favorite recipe list
    public Users updateFavorite(String username, Integer recipeId){
        Users x = usersRepository.findByUsername(username);// make copy of user
        if(x != null){                  // Makes sure that a user exists
            if(x.getFavoriteRecipes().contains(recipeId)){  // Checks to see if it has the recipeId in the list of favorites
                List<Integer> y = x.getFavoriteRecipes();
                y.remove(y.indexOf(recipeId)); // if so remove it
                x.setFavoriteRecipes(y); // push to copy
            } else {
                List<Integer> y = x.getFavoriteRecipes();
                y.add(recipeId);
                x.setFavoriteRecipes(y);
            }
            return usersRepository.save(x);// save result
        }
        return null;
    }



}
