package com.example.spring_security.controller;

import com.example.spring_security.entity.Role;
import com.example.spring_security.entity.User;
import com.example.spring_security.service.RoleService;
import com.example.spring_security.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String userPage2(Principal principal, Model model) {
        model.addAttribute("newUser", new User());
        Set<Role> roles = new HashSet<>(roleService.getAllRoles());
        model.addAttribute("allRoles", roles);
        User user = userService.findUsersByEmail(principal.getName());
        model.addAttribute("usera", user);
        model.addAttribute("users", userService.listUsers());
        return "adminpage";
    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        Set<Role> roles = new HashSet<>(roleService.getAllRoles());
//        model.addAttribute("allroles", roles);
//        return "/new";
//    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/test2/{id}")
    public String updateUser(@ModelAttribute("user") User updatedUser, @PathVariable("id") int id) {
        userService.updateUser(updatedUser, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
