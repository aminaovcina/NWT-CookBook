package com.example.recipeservice.models;
import javax.persistence.*;


@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    protected Category() {
    };
    public String getName() {
        return name;
    }
    
}
