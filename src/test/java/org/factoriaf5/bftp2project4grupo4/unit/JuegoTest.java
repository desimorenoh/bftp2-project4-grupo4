package org.factoriaf5.bftp2project4grupo4.unit;

import org.factoriaf5.bftp2project4grupo4.repositories.Juego;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JuegoTest {

    @Test
    public void losJuegosTienenUnPrecioNormalYOtroConDescuento() {

        Juego juego = new Juego();
        juego.setPrice(20);
        juego.setDiscount(10);

        assertThat(juego.getPriceWithDiscount(), equalTo(18.0));

    }

}

