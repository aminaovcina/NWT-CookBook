package com.example.recipeservice.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;


@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "category") //Jedan category moze imati vise recipea
    private List<RecipeCategory> rc;
    protected Category() {
    };
  
    public String getName() {
        return name;
    }
    
}
