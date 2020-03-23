package com.example.recipeservice.models;

import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "category") //Jedan category moze imati vise recipea
    private List<RecipeCategory> rc;
    protected Category() {
        super();
    };
    public Category(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Long getId(){
        return id;
    }
    
}
