package org.factoriaf5.bftp2project4grupo4;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void returnsAFormToAddNewGame() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/new"));
    }

    @Test
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/books/new")
                        .param("title", "Grand Theft Auto: San Andreas")
                        .param("platform", "PS2")
                        .param("year", "2004")
                        .param("price1", "24.99")
                        .param("discount", "10")
                        .param("price2", "15.99")
                        .param("category", "Action")
                        .param("publisher", "Take Two Interactive")
                        .param("pegi", "18")
                        .param("pegiContent", "extreme violence")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"))
        ;

        List<Juego> existingJuegos = (List<Juego>) juegoRepository.findAll();
        assertThat(existingJuegos, contains(allOf(
                hasProperty("title", equalTo("Grand Theft Auto: San Andreas")),
                hasProperty("platform", equalTo("PS2")),
                hasProperty("year", equalTo("2004")),
                hasProperty("discount", equalTo("10")),
                hasProperty("price2", equalTo("15.99")),
                hasProperty("category", equalTo("Action")),
                hasProperty("publisher", equalTo("Take Two Interactive")),
                hasProperty("pegi", equalTo("18")),
                hasProperty("pegiContent", equalTo("extreme violence"))

        )));
    }

    @Test
    void returnsAFormToAddNewJuegos() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attributeExists("juego"))
                .andExpect(model().attribute("title", "Create new juego"));
    }
}

