package org.example.databasepostgres.controller;

import org.example.databasepostgres.entities.UserEntity;
import org.example.databasepostgres.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ItemController {
@Autowired
    private DBService dbService;

    @GetMapping("/all-items")
    public String allItems(Model model) {
        model.addAttribute("items", dbService.getAllItems());
        return "all-items";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signInAsUser(UserEntity user, Model model) {
        Optional<UserEntity> users = dbService.getAllUsers()
                .stream()
                .filter(a -> a.getEmail().equals(user.getEmail()) && a.getPassword().equals(user.getPassword()))
                .findFirst();
        if (users.isPresent()) {
            model.addAttribute("user", users.get());
            return "sign-result";
        }
        else{
            model.addAttribute("error", "Invalid email or password.");
            return "sign-in";
        }

    }
}
