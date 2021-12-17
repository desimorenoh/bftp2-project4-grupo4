package org.factoriaf5.bftp2project4grupo4.controllers;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/juegos/new")
    String newJuego(Model model){
        Juego juego = new Juego();
        model.addAttribute("juego", juego);
        model.addAttribute("title", "Create new juego");
        return "juegos/edit";
    }

    @PostMapping("/juegos/new")
    String addBook(@ModelAttribute Juego juego) {
        juegoRepository.save(juego);
        return "redirect:/juegos";
    }

    @GetMapping("/juegos/edit/{id}")
    String editJuego(Model model, @PathVariable Long id){
        Juego juego = juegoRepository.findById(id).get();
        model.addAttribute("juego", juego);
        model.addAttribute("title", "Edit juego");
        return "juegos/edit";
    }
    @GetMapping("/juegos/delete/{id}")
    String remove(@PathVariable Long id) {
        juegoRepository.deleteById(id);
        return "redirect:/juegos";
    }
    @GetMapping("/home")
    String listJuegosOnHome(Model model) {
        List<Juego> juegos = (List<Juego>) juegoRepository.findAll();
        model.addAttribute("title", "Juego list");
        model.addAttribute("juegos", juegos);
        return "juegos/front";
    }
}