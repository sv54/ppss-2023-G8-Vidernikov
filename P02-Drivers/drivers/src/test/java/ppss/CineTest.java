package ppss;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

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
    @Tag("pruebaCine")
    @Tag("excluidoCine")
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
    @Tag("pruebaCine")
    public void reservaButacasC4() throws ButacasException {
        asientos = new boolean[]{true, true, true};
        solicitados = 1;
        boolean resultadoReal = cine.reservaButacasV1(asientos, solicitados);
        assertAll(
                () -> assertArrayEquals(new boolean[]{true, true, true}, asientos),
                () -> assertEquals(false, resultadoReal)
        );
    }

    @ParameterizedTest
    @Tag("parametrizado")
    @MethodSource("casosDePrueba")
    public void reservaButacasC5(boolean resultadoEsperado, boolean[] asientosEsperado,boolean[] asientos, int solicitados ) throws ButacasException {
        assertAll(
                ()->assertEquals(resultadoEsperado, cine.reservaButacasV1(asientos,solicitados)),
                ()->assertArrayEquals(asientosEsperado, asientos)

        );
    }

    private static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(false,new boolean[]{}, new boolean[]{},0),
                Arguments.of(true, new boolean[]{true, true, false,true, true},new boolean[]{false,false,false,true,true},2),
                Arguments.of(false,new boolean[]{true, true, true}, new boolean[]{true,true,true},1)
        );
    }
}
