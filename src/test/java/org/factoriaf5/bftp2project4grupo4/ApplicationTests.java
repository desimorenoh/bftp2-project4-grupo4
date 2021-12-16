package org.factoriaf5.bftp2project4grupo4;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
    @BeforeEach
    void setUp() {
        juegoRepository.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
    @Autowired
    JuegoRepository juegoRepository;

    @Test
    void returnsTheExistingJuegos() throws Exception {

        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));

        mockMvc.perform(get("/juegos"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(juego)));
    }

    @Test
    void returnsAFormToAddNewBooks() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/new"));
    }
}

