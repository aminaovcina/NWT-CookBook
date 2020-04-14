package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "favorites", schema = "targetSchemaName")
public class Favorites{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "User ID is mandatory")
    private Integer idUser;

    @NotNull(message = "Recipe ID is mandatory")
    private Integer idRecipe;


    public Favorites() {
    }

    public Favorites(Integer idUser, Integer idRec) {
        this.idUser = idUser;
        this.idRecipe = idRec;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
       this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }
    
    public void setIdUser(Integer id) {
       this.idUser = id;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }
    
    public void setIdRecipe(Integer id) {
       this.idRecipe = id;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", idSubUser='" + idUser + '\'' +
                ", idUser='" + idRecipe + '\'' +
                '}';
    }
}
