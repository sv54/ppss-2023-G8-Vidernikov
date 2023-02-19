package ppss;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CineTest {
    Cine cine;
    boolean[] asientos;
    int solicitados;

    @BeforeEach
    public void setup(){
        cine = new Cine();
    }

    @Test
    public void reservaButacasC1() throws ButacasException {

        asientos = new boolean[]{};
        solicitados = 3;
        boolean resultadoReal;
        ButacasException e = assertThrows(ButacasException.class, () -> cine.reservaButacasV1(asientos, solicitados) );
        assertEquals("No se puede procesar la solicitud", e.getMessage());
    }

    @Test
    public void reservaButacasC2() throws ButacasException {
        asientos = new boolean[]{};
        solicitados = 0;
        boolean resultadoReal;
        resultadoReal = cine.reservaButacasV1(asientos, solicitados);
        assertAll(
                () -> assertEquals(false, resultadoReal),
                () -> assertArrayEquals(new boolean[]{}, asientos)
        );
    }

    @Test
    public void reservaButacasC3() throws ButacasException {
        asientos = new boolean[]{false,false,false,true,true};
        solicitados = 2;
        boolean resultadoEsperado = true;
        boolean resultadoReal = cine.reservaButacasV1(asientos, solicitados);
        assertAll(
                () -> assertEquals(resultadoEsperado,resultadoReal),
                () -> assertArrayEquals(new boolean[]{true, true, false, true, true,}, asientos)
        );
    }

    @Test
    public void reservaButacasC4() throws ButacasException {
        asientos = new boolean[]{true, true, true};
        solicitados = 1;
        boolean resultadoReal = cine.reservaButacasV1(asientos, solicitados);
        assertAll(
                () -> assertArrayEquals(new boolean[]{true, true, true}, asientos),
                () -> assertEquals(false, resultadoReal)
        );
    }

}
