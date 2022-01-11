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
        )));

    }



    @Test
    @WithMockUser
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/juegos/new")
                        .with(csrf())
                        .param("title", "Grand Theft Auto: San Andreas")
                        .param("platform", "PS2")
                        .param("year", "2004")
                        .param("price", "24.99")
                        .param("discount", "10")
                        .param("priceWithDiscount", "15.99")
                        .param("category", "Action")
                        .param("publisher", "Take Two Interactive")
                        .param("pegi", "18")
                        .param("pegiContent", "extreme violence")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"))
        ;

        List<Juego> existingJuego = (List<Juego>) juegoRepository.findAll();
        assertThat(existingJuego, contains(allOf(
                hasProperty("title", equalTo("Grand Theft Auto: San Andreas")),
                hasProperty("platform", equalTo("PS2")),
                hasProperty("year", equalTo(2004)),
                hasProperty("price", equalTo(24.99)),
                hasProperty("discount", equalTo(10.0)),
                hasProperty("priceWithDiscount", equalTo(22.491)),
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
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
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
        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
        mockMvc.perform(get("/juegos/delete/" + juego.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/juegos"));

        assertThat(juegoRepository.findById(juego.getId()), equalTo(Optional.empty()));
    }

//    @Test
//    void allowsToKnowIfJuegoHaveADiscount() throws Exception {
//        Juego juego = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
//        mockMvc.perform(get("/juegos/edit" + juego.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("juegos/edit/{id}")
//                .andExpect(model().attributeExists("discount"));
//
//        assertThat(juegoRepository.findById("discount", equalTo(0));
//    }

    @Test
    @WithMockUser
    void allowsToSearchGamesByTitle() throws Exception {

        Juego juegoWithWord = juegoRepository.save(new Juego("Grand Theft Auto: San Andreas", "https://es.mmoga.net/images/games/_ext/1024789/gta-san-andreas-steam_large;width=360,height=340,05d311a32bbb6de46cc6a17b625586379de5a3ee.png", "PS2", 2004, 24.99, 10, 15.99, "Action", "Take Two Interactive", 18, "extreme violence"));
        Juego juegoWithoutWord = juegoRepository.save(new Juego("Nintendogs","https://www.mobygames.com/images/covers/l/200680-nintendogs-nintendo-ds-front-cover.jpg","DS",2005, 29.99,0,0,"Simulation","Nintendo",3,"suitable for kids"));

        mockMvc.perform(get("/juegos/search?word=Grand"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/front"))
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

        Juego rolePlayingJuegos = juegoRepository.save(new Juego("Pokemon Gold/Pokemon Silver","https://www.mobygames.com/images/covers/l/51564-pokemon-gold-version-game-boy-color-front-cover.jpg", "GB", 1999, 24.99, 0, 24.99, "Role-Playing","Nintendo", 7,""));
        Juego simulationJuegos = juegoRepository.save(new Juego("Nintendogs","https://www.mobygames.com/images/covers/l/200680-nintendogs-nintendo-ds-front-cover.jpg","DS",2005, 29.99,0,0,"Simulation","Nintendo",3,"suitable for kids"));

        mockMvc.perform(get("/juegos?category=Role-Playing"))
                .andExpect(status().isOk())
                .andExpect(view().name("juegos/all"))
                .andExpect(model().attribute("juegos", hasItem(rolePlayingJuegos)))
                .andExpect(model().attribute("juegos", not(hasItem(simulationJuegos))));
    }
}

