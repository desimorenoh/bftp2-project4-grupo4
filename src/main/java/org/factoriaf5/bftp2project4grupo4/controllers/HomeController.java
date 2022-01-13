package org.factoriaf5.bftp2project4grupo4.controllers;

import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.factoriaf5.bftp2project4grupo4.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    private JuegoRepository juegoRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public HomeController(JuegoRepository juegoRepository, CategoryRepository categoryRepository) {
        this.juegoRepository = juegoRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping(path = {"/", "/home", "/index"})
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("title", "Novedades!!!");
        model.addAttribute("juegos", juegoRepository.findAll());
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
