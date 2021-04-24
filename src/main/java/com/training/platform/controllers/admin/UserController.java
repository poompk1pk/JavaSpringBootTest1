package com.training.platform.controllers.admin;

import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @GetMapping("/demo")
    public String demo(){
        return "admin/sample";
    }

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("items", users);

        return "admin/user/lists";
    }

}

