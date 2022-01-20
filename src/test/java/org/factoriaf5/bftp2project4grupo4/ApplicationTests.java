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
                .andExpect(status().isOk());
    }
    @Autowired
    JuegoRepository juegoRepository;

    @Test
    @WithMockUser
    void returnsTheExistingJuegos() throws Exception {

        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", "18", "extreme violence"));

        mockMvc.perform(get("/juegos"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(juego)))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Simulation")),
                        hasProperty("name", is("Role-Playing")),
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Misc")),
                        hasProperty("name", is("Action")),
                        hasProperty("name", is("Platform"))
                )));

    }

    @Test
    @WithMockUser
    void returnsAFormToAddNewGame() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attributeExists("juego"))
                .andExpect(model().attribute("title", "Añadir Nuevo Juego"))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Simulation")),
                        hasProperty("name", is("Role-Playing")),
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Misc")),
                        hasProperty("name", is("Action")),
                        hasProperty("name", is("Platform"))
                )))
                .andExpect(model().attribute("pegi", hasItems(
                        hasProperty("name", is("3")),
                        hasProperty("name", is("7")),
                        hasProperty("name", is("10"))
                )));

    }


    @Test
    @WithMockUser
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/juegos/new")
                        .with(csrf())
                        .param("title", "Nintendogs")
                        .param("platform", "DS")
                        .param("year", "2005")
                        .param("price", "30.0")
                        .param("discount", "10")
                        .param("priceWithDiscount", "27.0")
                        .param("category", "Simulation")
                        .param("publisher", "Nintendo")
                        .param("pegi", "3")
                        .param("pegiContent", "Suitable for kids")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"))
        ;

        List<Juego> existingJuego = (List<Juego>) juegoRepository.findAll();
        assertThat(existingJuego, contains(allOf(
                hasProperty("title", equalTo("Nintendogs")),
                hasProperty("platform", equalTo("DS")),
                hasProperty("year", equalTo(2005)),
                hasProperty("price", equalTo(30.0)),
                hasProperty("discount", equalTo(10)),
                hasProperty("priceWithDiscount", equalTo(27.0)),
                hasProperty("category", equalTo("Simulation")),
                hasProperty("publisher", equalTo("Nintendo")),
                hasProperty("pegi", equalTo(3)),
                hasProperty("pegiContent", equalTo("Suitable for kids"))

        )));
    }

    @Test
    @WithMockUser
    void returnsAFormToAddNewJuegos() throws Exception {
        mockMvc.perform(get("/juegos/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attributeExists("juego"))
                .andExpect(model().attribute("title", "Añadir Nuevo Juego"))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Simulation")),
                        hasProperty("name", is("Role-Playing")),
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Misc")),
                        hasProperty("name", is("Action")),
                        hasProperty("name", is("Platform"))
                )));
    }

    @Test
    @WithMockUser
    void returnsAFormToEditJuegos() throws Exception {
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", "18", "extreme violence"));
        mockMvc.perform(get("/juegos/edit/" + juego.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/edit"))
                .andExpect(model().attribute("juego", juego))
                .andExpect(model().attribute("title", "Editar Juego"))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Simulation")),
                        hasProperty("name", is("Role-Playing")),
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Misc")),
                        hasProperty("name", is("Action")),
                        hasProperty("name", is("Platform"))
                )));
    }


    @Test
    @WithMockUser
    void allowsToDeleteAJuego() throws Exception {
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", "18", "extreme violence"));
        mockMvc.perform(get("/juegos/delete/" + juego.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"));

        assertThat(juegoRepository.findById(juego.getId()), equalTo(Optional.empty()));
    }


    @Test
    @WithMockUser
    void allowsToSearchGamesByTitle() throws Exception {

        Juego juegoWithWord = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", "18", "extreme violence"));
        Juego juegoWithoutWord = juegoRepository.save(new Juego("Nintendogs","https://www.mobygames.com/images/covers/l/200680-nintendogs-nintendo-ds-front-cover.jpg","DS",2005, 29.99,0,0,"Simulation","Nintendo","3","suitable for kids"));

        mockMvc.perform(get("/juegos/search?word=Grand"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("title", equalTo("Juegos containing \"Grand\"")))
                .andExpect(model().attribute("juegos", hasItem(juegoWithWord)))
                .andExpect(model().attribute("juegos", not(hasItem(juegoWithoutWord))))
                .andExpect(model().attribute("categories", hasItems(
                        hasProperty("name", is("Simulation")),
                        hasProperty("name", is("Role-Playing")),
                        hasProperty("name", is("Sports")),
                        hasProperty("name", is("Racing")),
                        hasProperty("name", is("Misc")),
                        hasProperty("name", is("Action")),
                        hasProperty("name", is("Platform"))
                )));

    }


    @Test
    @WithMockUser
    void returnsJuegosFromAGivenCategory() throws Exception {

        Juego rolePlayingJuegos = juegoRepository.save(new Juego("Pokemon Gold/Pokemon Silver","https://www.mobygames.com/images/covers/l/51564-pokemon-gold-version-game-boy-color-front-cover.jpg", "GB", 1999, 24.99, 0, 24.99, "Role-Playing","Nintendo", "7",""));
        Juego simulationJuegos = juegoRepository.save(new Juego("Nintendogs","https://www.mobygames.com/images/covers/l/200680-nintendogs-nintendo-ds-front-cover.jpg","DS",2005, 29.99,0,0,"Simulation","Nintendo","3","suitable for kids"));

        mockMvc.perform(get("/juegos?category=Role-Playing"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(rolePlayingJuegos)))
                .andExpect(model().attribute("juegos", not(hasItem(simulationJuegos))));
    }
}

