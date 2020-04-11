package com.example.recipeservice.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category") //Jedan category moze imati vise recipea
    @JsonIgnore
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
    public void setName(String name){
        this.name = name;
    }
    public Long getId(){
        return id;
    }
    
}
