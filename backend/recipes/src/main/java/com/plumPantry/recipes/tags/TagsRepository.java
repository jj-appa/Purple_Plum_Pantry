package com.plumPantry.recipes.tags;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends MongoRepository<Tag,String>
{     
    Tag findBytagName(String tagName);
}
