package com.example.recipeservice.models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date releasedate;
    private String description;
    private int preparationduration;
    @ManyToOne //vise recepata pripada istoj kategoriji
    private Category category;
    @ManyToOne //vise kategorija pripada istom tipu jela
    private Dish dish;
    @OneToOne
    private Post post;
    protected Recipe() {
    };
    //constructor
    public Recipe(String title, Date releasedate, String description, int duration){
        this.title = title;
        this.releasedate = releasedate;
        this.description = description;
        this.preparationduration = duration;
    }
    //getteri
    public String getTitle() {
        return title;
    }
    public Date getReleaseDate() {
        return releasedate;
    }
    public String getDescription() {
        return description;
    }
    public int getPreparationDuration(){
        return preparationduration;
    }

}