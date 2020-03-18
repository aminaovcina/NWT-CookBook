package com.example.recipeservice.models;
import javax.persistence.*;

@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    protected Dish() {
    };
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}