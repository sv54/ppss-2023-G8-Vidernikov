package ppss;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CineTest {
    Cine cine;

    @BeforeEach
    public void setup(){
        cine = new Cine();
    }

    @Test
    public void reservaButacasC1() throws ButacasException {

        boolean[] asientos = {};
        int solicitados = 3;
        boolean resultadoReal;
        ButacasException e = assertThrows(ButacasException.class, () -> cine.reservaButacasV1(asientos, solicitados) );
        assertEquals("No se puede procesar la solicitud", e.getMessage());
    }

}
