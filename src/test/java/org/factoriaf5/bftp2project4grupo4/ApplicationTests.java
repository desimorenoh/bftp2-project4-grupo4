package org.factoriaf5.bftp2project4grupo4;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.factoriaf5.bftp2project4grupo4.repositories.JuegoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    @WithMockUser
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
    @Autowired
    JuegoRepository juegoRepository;

    @Test
    @WithMockUser
    void returnsTheExistingJuegos() throws Exception {

        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));

        mockMvc.perform(get("/juegos"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(juego)));
    }

    @Test
    @WithMockUser
    void returnsAFormToAddNewGame() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attributeExists("juego"))
                .andExpect(model().attribute("title", "Añadir Nuevo Juego"));
    }



    @Test
    @WithMockUser
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/juegos/new")
                        .with(csrf())
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
                hasProperty("year", equalTo(2004)),
                hasProperty("price1", equalTo(24.99)),
                hasProperty("discount", equalTo(10)),
                hasProperty("price2", equalTo(15.99)),
                hasProperty("category", equalTo("Action")),
                hasProperty("publisher", equalTo("Take Two Interactive")),
                hasProperty("pegi", equalTo(18)),
                hasProperty("pegiContent", equalTo("extreme violence"))

        )));
    }

    @Test
    @WithMockUser
    void returnsAFormToAddNewJuegos() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attributeExists("juego"))
                .andExpect(model().attribute("title", "Añadir Nuevo Juego"));
    }
    @Test
    @WithMockUser
    void returnsAFormToEditJuegos() throws Exception {
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
        mockMvc.perform(get("/juegos/edit/" + juego.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attribute("juego", juego))
                .andExpect(model().attribute("title", "Edit Juego"));
    }
    @Test
    @WithMockUser
    void allowsToDeleteAJuego() throws Exception {
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
        mockMvc.perform(get("/juegos/delete/" + juego.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"));

        assertThat(juegoRepository.findById(juego.getId()), equalTo(Optional.empty()));
    }

}

