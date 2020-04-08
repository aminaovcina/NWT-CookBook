package com.example.recipeservice.helpers;

import java.io.Serializable;

public class UserDeseralizer implements Serializable {

private String name;
private String lastname;

public String getName(){
    return this.name;

}
public String getLastName(){
    return lastname;
}

}