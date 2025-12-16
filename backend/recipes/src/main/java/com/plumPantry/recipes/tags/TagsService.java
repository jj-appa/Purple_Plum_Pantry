package com.plumPantry.recipes.tags;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    //GET, return all tags
    public List<Tag> allTags(){
        return tagsRepository.findAll();
    }

    //GET, return specific Tag by tagName
    public Optional<Tag> singleTag(String tagName){
        Tag tag = tagsRepository.findBytagName(tagName);
        return Optional.ofNullable(tag);
    }



    //DELETE, delete specific Tag by tagName
    public String delete(String tagName) {
        Tag tag = tagsRepository.findBytagName(tagName);
        if (tag != null) {
            tagsRepository.delete(tag);
            return "Tag was deleted";
        } else {
            return "Tag: "+ tagName + " not found";
        }
    }
    //POST, inserting new Tag
    public Tag insert(Tag tag){
        return tagsRepository.insert(tag);
    }

    //PUT, update Tag name using id as search parameter
    public Tag updateTagName(ObjectId id, Tag newTag) {
        Optional<Tag> existingTagOptional = tagsRepository.findById(String.valueOf(id));
        if (existingTagOptional.isPresent()) {
            Tag existingTag = existingTagOptional.get();
            existingTag.setTagName(newTag.getTagName());
            return tagsRepository.save(existingTag);
        } else {
            return null;
        }
    }

    //PUT, update Tag creator using id as search parameter
    public Tag updateTagCreator(ObjectId id, Tag newTag) {
        Optional<Tag> existingTagOptional = tagsRepository.findById(String.valueOf(id));
        if (existingTagOptional.isPresent()) {
            Tag existingTag = existingTagOptional.get();
            return tagsRepository.save(existingTag);
        } else {
            return null;
        }
    }

}
