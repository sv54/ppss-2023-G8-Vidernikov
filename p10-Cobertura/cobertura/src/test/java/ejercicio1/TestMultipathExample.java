package ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultipathExample {

    MultipathExample multipathExample;

    @BeforeEach
    public void setup(){
        multipathExample = new MultipathExample();
    }

    @Test
    public void C1(){
        int resul = multipathExample.multiPath1(6,6,0);
        assertEquals(12, resul);
    }

    @Test
    public void C2(){
        int resul = multipathExample.multiPath1(3,3, 3);
        assertEquals(3,resul);
    }

    @Test
    public void C3(){
        int result = multipathExample.multiPath1(3,6,2);
        assertEquals(8, result);
    }

    @ParameterizedTest
    @MethodSource("Casos")
    public void c4(int a, int b, int c, int resul){
        assertEquals(resul, multipathExample.multiPath2(a,b,c));
    }

    private static Stream<Arguments> Casos(){
        return Stream.of(
            Arguments.of(6,4,6,16),
                Arguments.of(4,6,4,4),
                Arguments.of(6,6,4,4)
        );
    }

    @ParameterizedTest
    @MethodSource("Casos2")
    public void c5(int a, int b, int c, int resul){
        assertEquals(resul, multipathExample.multiPath3(a,b,c));
    }

    private static Stream<Arguments> Casos2(){
        return Stream.of(
                Arguments.of(6,4,6,16),
                Arguments.of(4,6,4,4),
                Arguments.of(6,6,4,4),
                Arguments.of(6,4,4,4)
        );
    }



}
