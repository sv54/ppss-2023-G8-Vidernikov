package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultipathExampleTest {

    MultipathExample example = new MultipathExample();
    int resul;
    @Test
    public void c1(){
        resul = example.multiPath1(6,6,0);
        Assertions.assertEquals(12, resul);
    }

    @Test
    public void c2(){
        resul = example.multiPath1(0,0,0);
        Assertions.assertEquals(0, resul);
    }

    @Test
    public void c3(){
        resul = example.multiPath1(3,6,2);
        Assertions.assertEquals(8, resul);
    }

    @ParameterizedTest
    @MethodSource("Casos1")
    public void c4(int esperado, int a, int b, int c){
        resul = example.multiPath2(a,b,c);
        Assertions.assertEquals(esperado, resul);
    }

    public static Stream<Arguments> Casos1(){
        return Stream.of(
                Arguments.of(16,6,4,6),
                Arguments.of(4,5,6,4),
                Arguments.of(0,6,5,0)
        );
    }

    @ParameterizedTest
    @MethodSource("Casos2")
    public void c5(int esperado, int a, int b, int c){
        resul = example.multiPath3(a,b,c);
        Assertions.assertEquals(esperado, resul);
    }

    public static Stream<Arguments> Casos2(){
        return Stream.of(
                Arguments.of(16,6,4,6),
                Arguments.of(4,5,6,4),
                Arguments.of(0,6,5,0)
        );
    }
}
