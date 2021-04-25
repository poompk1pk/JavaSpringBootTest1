package com.training.platform.controllers.admin;

import com.training.platform.entities.Pager;
import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import com.training.platform.validators.Default;
import com.training.platform.validators.Extended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @GetMapping("/demo")
    public String demo(){
        return "admin/sample";
    }

    @Autowired
    private UserService userService;

    @GetMapping(value="/create")
    public String create(Model model, User user) {
        model.addAttribute("cities", userService.getCities());
        model.addAttribute("user", user);
        return "admin/user/create";
    }



    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @GetMapping(value = "")
    public String index(
            Model model,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) throws Exception {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<User> users = userService.findAll(PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("items", users);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        return "admin/user/lists";
    }



    @PostMapping(value="")
    public String store(@Validated({ Default.class, Extended.class })  User user,
                        BindingResult bindingResult,
                        @RequestParam Map<String,String> inputs,
                        RedirectAttributes redirAttrs,
                        Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", userService.getCities());
            model.addAttribute("user", user);
            return "admin/user/create";
        }
        //Saving data
        userService.save(inputs);
        redirAttrs.addFlashAttribute("success", "User [" +
                inputs.get("name") + " " +
                inputs.get("surname") + "] " +
                "created successfully.");
        return "redirect:/admin/user";
    }


    @GetMapping(value="/{id}/edit")
    public String edit(@PathVariable String id, Model model) throws Exception {
        Optional<User> user = userService.findById(Integer.parseInt(id));
        model.addAttribute("cities", userService.getCities());
        model.addAttribute("user", user);
        model.addAttribute("email", user.get().getEmail());

        return "admin/user/edit";
    }



    @PatchMapping (value="/{id}")
    public String update(@PathVariable String id,
                         @Validated({ Default.class }) User user,
                         BindingResult bindingResult,
                         @RequestParam Map<String,String> inputs,
                         RedirectAttributes redirAttrs,
                         Model model) throws Exception {

        Optional<User> obj = userService.findById(Integer.parseInt(id));
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", userService.getCities());
            model.addAttribute("user", user);
            model.addAttribute("email", obj.get().getEmail());
            return "admin/user/edit";
        }
        //Update Data
        userService.update(obj, inputs);
        redirAttrs.addFlashAttribute("success", "User [" +
                inputs.get("name") + " " +
                inputs.get("surname") + "] " +
                "updated successfully.");

        return "redirect:/admin/user";
    }


    @DeleteMapping(value = "/{id}")
    public String destroy(@PathVariable String id, RedirectAttributes redirAttrs) throws Exception {

        Optional<User> user = userService.findById(Integer.parseInt(id));
        if (!user.isPresent()) {
            redirAttrs.addFlashAttribute("error", "User ID : " + id + " not found.");
        } else {
            //Delete user by id
            userService.deleteById(Integer.parseInt(id));
            redirAttrs.addFlashAttribute("success", "User [" +
                    user.get().getName() + " " +
                    user.get().getSurname() + "] " +
                    "deleted successfully.");
        }
        return "redirect:/admin/user";
    }







}

