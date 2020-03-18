package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String title;
    private Date releasedate;
    private String description;
    private int preparationduration;
    // @ManyToOne //vise recepata pripada istoj kategoriji
    // private Category category;
    // @ManyToOne
    // private User creator;
    protected User() {
    };
    //constructor

    //getteri
    public String getTitle() {
        return title;
    }
}