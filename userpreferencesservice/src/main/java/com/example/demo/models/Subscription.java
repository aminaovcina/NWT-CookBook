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
@Table(name = "subscription", schema = "targetSchemaName")
public class Subscription{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Subscriber ID is mandatory")
    @Column(name = "idSubUser")
    private Integer idSubUser;

    @NotNull(message = "Subscribed user ID is mandatory")
    @Column(name = "idUser")
    private Integer idUser;

    @Column(name = "type")
    private Integer type;

    public Subscription() {
        this.idSubUser = 4;
        this.idUser = 4;
        this.type = 4;
    }

    public Subscription(Integer idSub, Integer id, Integer type) {
        this.idSubUser = idSub;
        this.idUser = id;
        this.type = type;
    }

    public Subscription(Integer idSub, Integer id) {
        this.idSubUser = idSub;
        this.idUser = id;
        this.type = 0;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
       this.id = id;
    }

    public Integer getIdSubUser() {
        return idSubUser;
    }
    
    public void setIdSubUser(Integer id) {
       this.idSubUser = id;
    }

    public Integer getIdUser() {
        return idUser;
    }
    
    public void setIdUser(Integer id) {
       this.idUser = id;
    }

    public Integer getType() {
        return type;
    }
    
    public void setType(Integer id) {
       this.type = id;
    }
    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", idSubUser='" + idSubUser + '\'' +
                ", idUser='" + idUser + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
