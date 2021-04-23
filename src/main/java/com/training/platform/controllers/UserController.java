package com.training.platform.controllers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.training.platform.entities.Shop;
import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping(value = "")
    public List<User> index() {
        return findAll();
    }

    @GetMapping(value = "findAll") //
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping(value = "findById") // findById?id=1
    public User findById(@RequestParam int id) {
        return userService.findById(id).get();
    }

    @GetMapping(value = "findAllByLimit") // findAllByLimit?start=1&limit=10&field=id
    public Page<User> findById(@RequestParam int start, @RequestParam int limit, @RequestParam String field) {
        return userService.findAllByLimit(start,limit,field);
    }

    @GetMapping(value = "findByCityAndActiveAndAge") // findByCityAndActiveAndAge?city=bangkok&active=1&age=20
    public List<User> findByCityAndActiveAndAge(@RequestParam String city ,@RequestParam int active, @RequestParam int age) {

        return userService.findByCityAndActiveAndAge(city, active, age);
    }

    @GetMapping(value = "age2")
    public List<User> getAgeByParam(@RequestParam(name = "age") List<Integer> listAge) {
        return userService.findByAgeIn(listAge);
    }

    @GetMapping(value = "findByAgeIn") // findByAgeIn?ages=10,21,22,23
    public List<User> findByAgeIn(@RequestParam String ages) {

        return userService.findByAgeIn(ages);
    }

    @GetMapping(value = "findAllByQuery") //
    public List<User> findAllByQuery() {
        return userService.findAllByQuery();
    }

    @GetMapping(value = "findAllByParamsQuery") // findAllByParamsQuery?active=1&city=bangkok
    public List<User> findAllByParamsQuery(@RequestParam int active, @RequestParam String city) {
        return userService.findAllByParamsQuery(active, city);
    }

    @GetMapping(value = "findAllByJpqlQuery") //
    public List<User> findAllByJpqlQuery() {
        return userService.findAllByJpqlQuery();
    }

    @GetMapping(value = "findAllByJpqlParamsQuery") // findAllByJpqlParamsQuery?active=1&city=bangkok
    public List<User> findAllByJpqlParamsQuery(@RequestParam int active, @RequestParam String city) {

        return userService.findAllByJpqlParamsQuery(active, city);
    }


}
