package com.example.recipeservice.models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date postdate;
    @ManyToOne //vise recepata pripada istom korisniku
    private Account creator;
    protected Post() {
    };
    public String getTitle() {
        return title;
    }
    public Date getPostDate() {
        return postdate;
    }

}