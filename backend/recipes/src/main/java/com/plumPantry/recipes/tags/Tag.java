package com.plumPantry.recipes.tags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    private ObjectId id;

    private Integer tagID;
    private String tagName;
}

