package org.factoriaf5.bftp2project4grupo4.controllers;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("title", "Lista de Juegos");
        model.addAttribute("juegos", juegos);
        return "juegos/all";
    }

    @GetMapping("/juegos/new")
    String newJuego(Model model){
        Juego juego = new Juego();
        model.addAttribute("juego", juego);
        model.addAttribute("title", "Añadir Nuevo Juego");
        return "juegos/edit";
    }

    @PostMapping("/juegos/new")
    String addJuego(@ModelAttribute Juego juego) {
        juegoRepository.save(juego);
        return "redirect:/juegos";
    }

    @GetMapping("/juegos/edit/{id}")
    String editJuego(Model model, @PathVariable Long id){
        Juego juego = juegoRepository.findById(id).get();
        model.addAttribute("juego", juego);
        model.addAttribute("title", "Editar Juego");
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
        model.addAttribute("title", "Novedades!!!");
        model.addAttribute("juegos", juegos);
        return "juegos/front";
    }

    @GetMapping("/juegos/search")
    String searchJuego(@RequestParam String word, Model model) {
        List<Juego> juegos = juegoRepository.findJuegoByTitleContaining(word);
        model.addAttribute("title", String.format("Juegos containing \"%s\"", word));
        model.addAttribute("juegos", juegos);

        return "juegos/front";
    }

}