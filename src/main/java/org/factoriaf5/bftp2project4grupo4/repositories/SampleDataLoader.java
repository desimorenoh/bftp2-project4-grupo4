package org.factoriaf5.bftp2project4grupo4.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private JuegoRepository juegoRepository;

    @Autowired
    public SampleDataLoader(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        juegoRepository.saveAll(List.of(

                new Juego("Wii Sports","jsfjdjf","Wii", 2006, 19.99,0,19.99,"Sports", "Nintendo", 7,"sf"),
                new Juego("Super Mario Bros.","","NES",1985, 14.99, 0,0,"Platform","Nintendo",7,"")

        ));
    }
}