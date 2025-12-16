package com.plumPantry.recipes.ingredients;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    private ObjectId id;
    private Integer ingID;
    private String ingName;

}
