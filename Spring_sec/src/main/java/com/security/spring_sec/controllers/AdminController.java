package com.security.spring_sec.controllers;


import com.security.spring_sec.model.Role;
import com.security.spring_sec.model.User;
import com.security.spring_sec.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServices userServices;

    @Autowired
    public AdminController(UserServices userService) {
        this.userServices = userService;
    }

    @GetMapping()
    public String adminStartPage(Model model){
        model.addAttribute("users", userServices.getListUser());
        model.addAttribute("formUser", new User());
        model.addAttribute("allRoles", userServices.getAllRoles());
        return "admin_start_page";
    }


    //Настроить прием листа, из которого нужно получить сет листов и поместить его юзера, а потом сохранить этого юзера
    @PostMapping(value = "/create")
    public String create(@ModelAttribute("users") User user, @RequestParam("role") List<String> values){
        userServices.add(user, values);
        return "redirect:/admin";
    }
// list of role, каждый лист содержит в себе роль

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("users") User user, @PathVariable("id") Long id) {
        userServices.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userServices.delete(id);
        return "redirect:/admin";
    }





}
