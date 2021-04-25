package com.training.platform.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.training.platform.validators.FieldsValueMatch;
import com.training.platform.validators.UniqueEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

// lombok
@Data
// Entity class mapping with table
@Entity
// table name = users
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id") // To prevention for User.Shop.User.Shop infinity loop for joinning object
@FieldsValueMatch(field = "password",
        fieldMatch = "confirm_password",
        message = "The password fields must match")
@EqualsAndHashCode(exclude="shop")
@ToString(exclude = {"shop"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    @NotEmpty(message = "Name is a required field")
    @Size(min=2,  message="Name should have at least 2 characters")
    @Size(max = 100,  message="Name should have not over 5 characters")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname is a required field")
    @Size(min=2,  message="Surname should have at least 2 characters")
    @Size(max = 100,  message="Surname should have not over 5 characters")
    private String surname;

    @Column(name = "email")
    @NotEmpty(message = "Email is a required field")
    @Email(message = "Email is a email pattern")
    @UniqueEmail(message = "There is already user with this email!")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password is a required field")
    private String password;

    @JsonIgnore
    @Transient
    @NotEmpty(message = "Confirm Password is a required field")
    private String confirm_password;

    @Column(name = "age")
    @Digits(integer=2, fraction=0, message = "Age is a only numeric and between 15 to 60 years old")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    @NotEmpty(message = "City is a required field")
    private String city;



    @Column(name = "mobile")
    private String mobile;

    @Column(name = "active")
    private Integer active;

    @Column(name = "api_token")
    private String api_token;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Shop shop;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
