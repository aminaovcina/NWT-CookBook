package com.example.recipeservice.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String title;

    @Size(max = 300)
    private String description;

    private int cookingtemperature;

    private int cookingtime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date postdate;

    @OneToMany(mappedBy = "recipe") //Jedan recipe moze imati vise categorya
    @JsonIgnore
    private List<RecipeCategory> rc;

    private Long account_id;
    

    @ManyToOne //vise recepata pripada istom tipu jela
    @JoinColumn(name="dish_id")
    private Dish dish;

    protected Recipe() {
        super();
    };
    //constructor
    public Recipe(String title, String description, int duration, int temperature, Dish dish, Date date, Long account){
        this.title = title;
        this.description = description;
        this.cookingtime = duration;
        this.cookingtemperature = temperature;
        this.dish = dish;
        this.account_id = account;
        this.postdate = date;
    };
    //getteri
    public Long getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public int getCookingTime(){
        return cookingtime;
    }
    public void setCookingTime(int time){
        this.cookingtime = time;
    }
    public int getCookingTemperature(){
        return cookingtemperature;
    }
    public void setCookingTemperature(int temp){
        this.cookingtemperature = temp;
    }
    public Dish getDish(){
        return dish;
    }
    public Long getDishId(){
        return dish.getId();
    }
    public Long getAccountId(){
        return account_id;
    }
    public Date getPostDate(){
        return postdate;
    }

}