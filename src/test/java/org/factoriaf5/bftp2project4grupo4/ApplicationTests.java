package org.factoriaf5.bftp2project4grupo4;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
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

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void returnsTheExistingJuegos() throws Exception {

        Juego juego = juegoRepository.save(new Juego(1,"Grand Theft Auto: San Andreas", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take-Two Interactive", 18, "extreme violence"));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(juego)));
    }
}

