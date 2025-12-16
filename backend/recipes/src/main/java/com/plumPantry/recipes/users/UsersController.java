package com.plumPantry.recipes.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/db/Users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    //Mapping

    // GET localhost:8080/db/Users, Return all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(usersService.allUsers(), HttpStatus.OK);
    }
    // GET localhost:8080/db/Users/{username}, Return specific user by username
    @GetMapping("/{username}")
    public ResponseEntity<Optional<Users>> getSingleUser(@PathVariable String username){
        return new ResponseEntity<>(usersService.singleUser(username), HttpStatus.OK);
    }

    //GET localhost:8080/db/Users/check/{username}/{userCred}, Checking to see if credentials given is matching to credentials  in database
    @GetMapping("/check/{username}/{userCred}")
    public ResponseEntity<Object> checkUserCred(@PathVariable String username, @PathVariable String userCred ) {
        if(usersService.checkUserCred(username, userCred)){
            return new ResponseEntity<>(username, HttpStatus.OK);
        }
        return new ResponseEntity<>(0, HttpStatus.OK);
    }
    


    //DELETE localhost:8080/db/Users/deleteByUsername/{username-here}, delete by username
    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        String result = usersService.deleteUserByUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST localhost:8080/db/Users, Creating new User using json format in request
    @PostMapping
    public ResponseEntity<Object> insertUser(@RequestBody Users user){
        if(user.getEmail()==null){                  // Makes sure an email is put in
            return new ResponseEntity<>((String)"Did not enter an email", HttpStatus.OK);
        }
        if(user.getUsername()==null){               // Makes sure an username is put in
            return new ResponseEntity<>((String)"Did not enter an username", HttpStatus.OK);
        }
        if(user.getPassword()==null){
            return new ResponseEntity<>((String)"Did not enter a password", HttpStatus.OK);
        }

        if(usersService.checkUsernameDB(user.getUsername())){
            return new ResponseEntity<>((String)"The username is taken", HttpStatus.OK);
        }
        if(usersService.checkEmailDB(user.getEmail())){
            return new ResponseEntity<>((String)"The email is taken", HttpStatus.OK);
        }

        return new ResponseEntity<>(usersService.insertUser(user), HttpStatus.OK);
    }
    // PUT http://localhost:8080/db/Users/update/{userId}, updating existing user using userId
    @PutMapping("update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer userId, @RequestBody Users user) {

        if(usersService.checkUsernameDB(user.getUsername())){
            return new ResponseEntity<>((String)"The username is taken", HttpStatus.OK);
        }
        if(usersService.checkEmailDB(user.getEmail())){
            return new ResponseEntity<>((String)"The email is taken", HttpStatus.OK);
        }

        return new ResponseEntity<>(usersService.updateUser(userId, user), HttpStatus.OK);
    }
    
    
    // PUT http://localhost:8080/db/Users/favorite/{userId}, updating a users favorite recipe list
    @PutMapping("favorite/{username}/{recipeId}")
    public ResponseEntity<Object> favoriteRecipe(@PathVariable String username, @PathVariable Integer recipeId ) {
        return new ResponseEntity<>(usersService.updateFavorite(username, recipeId), HttpStatus.OK);

    }







}
