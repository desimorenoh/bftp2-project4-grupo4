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
                Nintendogs;DS;2005;29,99;;;Simulation;Nintendo;3;suitable for kids;
            Mario Kart DS;DS;2005;19,99;;;Racing;Nintendo;7;;
        Pokemon Gold/Pokemon Silver;GB;1999;24,99;;;Role-Playing;Nintendo;7;;
        Wii Fit;Wii;2007;9,99;;;Sports;Nintendo;3;suitable for kids;
        Wii Fit Plus;Wii;2009;8,99;;;Sports;Nintendo;3;suitable for kids;
        Kinect Adventures!;X360;2010;12,99;;;Misc;Microsoft Game Studios;7;;
        Grand Theft Auto V;PS3;2013;24,99;;;Action;Take-Two Interactive;18;extreme violence;
        Grand Theft Auto: San Andreas;PS2;2004;24,99;10;22,49;Action;Take-Two Interactive;18;extreme violence;
        Super Mario World;SNES;1990;49,99;10;44,99;Platform;Nintendo;7;;
        ));
    }
}