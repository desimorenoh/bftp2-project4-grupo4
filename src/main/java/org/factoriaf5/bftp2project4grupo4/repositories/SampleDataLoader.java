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

                new Juego ("Nintendogs","../img/3.png","DS",2005, 29.99,0,29.99,"Simulation","Nintendo",3,"Suitable for kids"),
                new Juego("Animal Crossing","../img/2.png","DS", 2005, 29.99,0,29.99,"Simulation", "Nintendo", 3,"Suitable for Kids"),
                new Juego("Pokemon Gold","../img/5.png", "GB", 1999, 24.99, 0, 24.99, "Role-Playing","Nintendo", 7,"Suitable for kids"),
                new Juego("FIFA 16", "../img/8.png", "PS4", 2015, 9.99, 10, 8.99, "Sports", "Electronic Arts", 7, "EveryOne"),
                new Juego("Mario Kart DS","../img/1.png","DS",2005,19.99,0,19.99,"Racing","Nintendo",7,"Suitable for kids"),
                new Juego("Wii Sports","../img/10.png","Wii", 2006, 19.99,0,19.99,"Sports", "Nintendo", 7,"EveryOne"),
                new Juego("Wii Fit", "../img/4.png", "Wii", 2007, 9.99, 0, 9.99, "Sports", "Nintendo", 3, "Suitable for kids"),
                new Juego("Wii Fit Plus", "../img/12.png", "Wii", 2009, 8.99, 0, 8.99, "Sports", "Nintendo", 3, "Suitable for kids"),
                new Juego("Skylanders Superchargers", "../img/7.png", "Wii", 2005, 13.99, 0, 13.99, "Action", "Nintendo", 10, "EveryOne"),
                new Juego("Cars 3", "../img/11.png", "PS4", 2007, 29.99, 0, 29.99, "Racing", "Nintendo", 3, "suitable for kids"),
                new Juego("Kinect Adventures!", "../img/9.png", "X360", 2010, 12.99, 0, 12.99, "Misc", "Microsoft Game Studios",7,"Suitable for kids"),
                new Juego("Super Mario World", "../img/6.png", "SNES", 1990, 49.99, 10, 44.99, "Platform", "Nintendo", 7,"EveryOne")
        ));
    }
}