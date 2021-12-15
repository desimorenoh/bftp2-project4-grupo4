package org.factoriaf5.bftp2project4grupo4.controllers;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JuegoController {

    private JuegoRepository juegoRepository;

    @Autowired
    public JuegoController(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    @GetMapping("/juegos")
    String listJuegos(Model model) {
        List<Juego> juegos = (List<Juego>) juegoRepository.findAll();
        model.addAttribute("title", "Juego list");
        model.addAttribute("juegos", juegos);
        return "juegos/all";
    }
}