package com.plumPantry.recipes.tags;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/db/Tags")
public class TagController {

    @Autowired
    private TagsService tagsService;
    //Mapping

    // GET localhost:8080/db/Tags, Return all ingredient tag records in the Tags collection.
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        return new ResponseEntity<>(tagsService.allTags(), HttpStatus.OK);
    }
    // GET localhost:8080/db/Tags/{name}, Return specific ingredient tag record by searching tagName
    @GetMapping("/get/{tagName}")
    public ResponseEntity<Optional<Tag>> getTag(@PathVariable String tagName){
        return new ResponseEntity<>(tagsService.singleTag(tagName), HttpStatus.OK);
    }

    //DELETE localhost:8080/db/Tags/delete/{name-here}, delete tag record by tagName
    @DeleteMapping("/delete/{tagName}")
    public ResponseEntity<String> deleteTagByName(@PathVariable String tagName) {
        String result = tagsService.delete(tagName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST localhost:8080/db/Tags/post, Creating new tag using json format in request
    @PostMapping("post")
    public ResponseEntity<Tag> insertTag(@RequestBody Tag tag){
        return new ResponseEntity<>(tagsService.insert(tag), HttpStatus.OK);
    }
    // PUT http://localhost:8080/db/Tags/update/{id}, updating existing tag using tag database id.
    @PutMapping("updateTagName/{id}")
    public ResponseEntity<Tag> updateTagName(@PathVariable ObjectId id, @RequestBody Tag tag) {
        return new ResponseEntity<>(tagsService.updateTagName(id, tag), HttpStatus.OK);
    }

    @PutMapping("updateTagCreator/{id}")
    public ResponseEntity<Tag> updateTagCreator(@PathVariable ObjectId id, @RequestBody Tag tag) {
        return new ResponseEntity<>(tagsService.updateTagCreator(id, tag), HttpStatus.OK);
    }
}