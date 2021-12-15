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
               // new Juego("Una habitación propia", "Virginia Woolf", "Essay"),
                new Juego("Wii Sports", "Wii", 2006, 19.99,0,19.99,"Sports", "Nintendo", 7,""),
                new Juego()

        ));
    }
}