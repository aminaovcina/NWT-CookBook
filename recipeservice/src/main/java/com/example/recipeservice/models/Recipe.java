package com.example.recipeservice.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max = 128)
    private String title;
    @Size(max = 300)
    private String description;
    private int cookingtemperature;
    private int cookingtime;
    @Temporal(TemporalType.DATE)
    private Date postdate;
    @OneToMany(mappedBy = "recipe") //Jedan recipe moze imati vise categorya
    private List<RecipeCategory> rc;
    @ManyToOne //vise recepata pripada istom tipu jela
    @JoinColumn(name="dish_id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    protected Recipe() {
        super();
    };
    //constructor
    public Recipe(String title, String description, int duration, int temperature, Dish dish, List<RecipeCategory> rc, Date date, Account account){
        this.title = title;
        this.description = description;
        this.cookingtime = duration;
        this.cookingtemperature = temperature;
        this.dish = dish;
        this.rc = rc;
        this.account = account;
        this.postdate = date;
    }
    //getteri
    public Long getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCookingTime(){
        return cookingtime;
    }
    public int getCookingTemperature(){
        return cookingtemperature;
    }
    public Dish getDish(){
        return dish;
    }
    public Long getDishId(){
        return dish.getId();
    }
    public Long getAccountId(){
        return account.getId();
    }
    public Date getPostDate(){
        return postdate;
    }

}