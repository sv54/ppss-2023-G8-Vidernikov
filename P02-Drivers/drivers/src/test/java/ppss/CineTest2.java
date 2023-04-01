package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;



public class CineTest2 {

    boolean[] asientos;
    int solicitados;
    Cine c;
    boolean [] asientosEsperado;

    @BeforeEach
    public void setup(){
        c = new Cine();
    }

    @Tag("cine2")
    @Test
    public void reservaButacasC1(){

        asientos = new boolean[]{};
        solicitados = 3;
        String esperado = "No se puede procesar la solicitud";
        ButacasException resultado = assertThrows(ButacasException.class ,()->c.reservaButacasV1(asientos, 3));

        assertEquals(esperado, resultado.getMessage());
    }

    @Tag("cine2")
    @Test
    public void reservaButacasC2(){
        asientos = new boolean[]{};
        solicitados = 0;
        asientosEsperado = new boolean[]{};
        boolean esperado = false;

        boolean resultado = assertDoesNotThrow(() -> c.reservaButacasV1(asientos, solicitados));

        assertAll(
                () -> assertEquals(esperado,resultado),
                () -> assertArrayEquals(asientosEsperado, asientos)
        );
    }
    @Tag("cine2")
    @Test
    public void reservaButacasC3(){
        asientos = new boolean[]{false,false,false,true,true};
        asientosEsperado = new boolean[]{true, true,false,true,true};
        solicitados = 2;
        boolean esperado = true;

        boolean resultado = assertDoesNotThrow(() -> c.reservaButacasV1(asientos,solicitados));
        assertAll(
                () -> assertArrayEquals(asientosEsperado, asientos),
                () -> assertEquals(esperado, resultado)
        );
    }

    @Tag("cine2")
    @Test
    public void reservaButacasC4(){
        asientos = new boolean[]{true,true,true};
        asientosEsperado = new boolean[]{true,true,true};
        solicitados = 1;

        boolean esperado = false;

        boolean resultado = assertDoesNotThrow(() -> c.reservaButacasV1(asientos,solicitados));
        assertAll(
                () -> assertArrayEquals(asientosEsperado, asientos),
                () -> assertEquals(esperado, resultado)
        );

    }

    @ParameterizedTest
    @MethodSource("casosDePrueba")
    void reservaButacasC5(boolean[] expectedAseintos, boolean esperado, int solicitados, boolean[] asientos){
        boolean resultado = assertDoesNotThrow(() -> c.reservaButacasV1(asientos, solicitados ));

        assertAll(
                () -> assertArrayEquals(expectedAseintos, asientos),
                () -> assertEquals(esperado, resultado)
        );

    }

    private static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(new boolean[]{},false, 0, new boolean[]{}),
                Arguments.of(new boolean[]{true, true, false, true, true},true,2, new boolean[]{false,false,false,true,true}),
                Arguments.of(new boolean[]{true, true, true},false, 1, new boolean[]{true,true,true})
        );
    };
}
