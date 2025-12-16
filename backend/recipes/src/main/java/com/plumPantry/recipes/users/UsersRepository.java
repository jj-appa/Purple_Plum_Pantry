package com.plumPantry.recipes.users;

import org.springframework.data.mongodb.repository.MongoRepository;




public interface UsersRepository extends MongoRepository<Users,String>{
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users findByUserId(Integer userId);

}
