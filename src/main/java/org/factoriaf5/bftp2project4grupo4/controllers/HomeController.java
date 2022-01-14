package org.factoriaf5.bftp2project4grupo4.controllers;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;
import org.factoriaf5.bftp2project4grupo4.repositories.PegiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.factoriaf5.bftp2project4grupo4.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class HomeController {
    private JuegoRepository juegoRepository;
    private CategoryRepository categoryRepository;
    private PegiRepository pegiRepository;

    @Autowired
    public HomeController(JuegoRepository juegoRepository, CategoryRepository categoryRepository, PegiRepository pegiRepository) {
        this.juegoRepository = juegoRepository;
        this.categoryRepository = categoryRepository;
        this.pegiRepository = pegiRepository;
    }
    @GetMapping(path = {"/", "/home", "/index"})
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("title", "Novedades!!!");
        model.addAttribute("juegos", juegoRepository.findAll());
        model.addAttribute("pegi", pegiRepository.findAll());
        return "/home";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
