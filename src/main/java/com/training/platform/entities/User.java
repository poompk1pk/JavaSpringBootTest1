package com.training.platform.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

// lombok
@Data
// Entity class mapping with table
@Entity
// table name = users
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id") // To prevention for User.Shop.User.Shop infinity loop for joinning object
public class User implements Serializable {

    @Id
    // auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;

    @Column(name = "email")

    private String email;

    @Column(name = "password")
    private String password;

    @Transient // Transient is not connect with db, only normally method
    private String confirm_password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "active")
    private Integer active;

    @Column(name = "api_token")
    private String api_token;

    @Column(name = "remember_token")
    private String rememberToken;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Shop shop;
}
