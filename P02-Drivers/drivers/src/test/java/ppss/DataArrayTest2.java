package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataArrayTest2 {

    DataArray da;
    String esperadoExcepcion;
    int[] coleccion;
    int[] coleccionEsperado;
    int elem;




    @Test
    public void deleteC1(){
        coleccion = new int[]{1,3,5,7};
        coleccionEsperado = new int[]{1,3,7};
        elem = 5;
        da = new DataArray(coleccion);

        assertAll(
               () -> assertDoesNotThrow(() -> da.delete(elem)),
               () -> assertArrayEquals(coleccionEsperado, da.getColeccion())
        );
    }
    @Test
    public void deleteC2(){
        coleccion = new int[]{1,3,3,5,7};
        coleccionEsperado = new int[]{1,3,5,7};
        elem = 3;
        da = new DataArray(coleccion);

        assertAll(
                () -> assertDoesNotThrow(() -> da.delete(elem)),
                () -> assertArrayEquals(coleccionEsperado, da.getColeccion())
        );
    }

    @Test
    public void deleteC3(){
        coleccion = new int[]{1,2,3,4,5,6,7,8,9,10};
        coleccionEsperado = new int[]{1,2,3,5,6,7,8,9,10};
        elem = 4;
        da = new DataArray(coleccion);

        assertAll(
                () -> assertDoesNotThrow(() -> da.delete(elem)),
                () -> assertArrayEquals(coleccionEsperado, da.getColeccion())
        );
    }

    @Test
    public void deleteC4(){
        coleccion = new int[]{};
        esperadoExcepcion = "No hay elementos en la colección";
        elem = 8;
        da = new DataArray(coleccion);
        DataException resul = assertThrows(DataException.class,() -> da.delete(elem));
        assertEquals(esperadoExcepcion, resul.getMessage() );
    }

    @Test
    public void deleteC5(){
        coleccion = new int[]{1,3,5,7};
        esperadoExcepcion = "El valor a borrar debe ser > 0";
        elem = -5;
        da = new DataArray(coleccion);
        DataException resul = assertThrows(DataException.class,() -> da.delete(elem));
        assertEquals(esperadoExcepcion, resul.getMessage() );
    }


    @ParameterizedTest
    @MethodSource("casosDePrueba")
    void deleteC8(int[] cEsperado,int[] coleccion,int elem){
        da = new DataArray(coleccion);

        assertAll(
                () -> assertDoesNotThrow(() -> da.delete(elem)),
                () -> assertArrayEquals(cEsperado, da.getColeccion())
        );
    }

    @ParameterizedTest
    @MethodSource("casosDePrueba2")
    void deleteC9(String mensajeEsperado,int[] coleccion,int elem){
        da = new DataArray(coleccion);

        DataException resul = assertThrows(DataException.class, () -> da.delete(elem));
        assertEquals(mensajeEsperado, resul.getMessage());
    }



    static private Stream<Arguments> casosDePrueba2(){
        return Stream.of(
                Arguments.of("No hay elementos en la colección",new int[]{},8),
                Arguments.of("El valor a borrar debe ser > 0",new int[]{1,3,5,7},-5),
                Arguments.of("Colección vacía. Y el valor a borrar debe ser > 0",new int[]{},-5),
                Arguments.of("Elemento no encontrado", new int[]{1,3,5,7},8)
        );
    }
    static private Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(new int[]{1,3,7},new int[]{1,3,5,7},5),
                Arguments.of(new int[]{1,3,5,7},new int[]{1,3,3,5,7},3),
                Arguments.of(new int[]{1,2,3,5,6,7,8,9,10},new int[]{1,2,3,4,5,6,7,8,9,10},4)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    void palindromes(String candidate) {
        System.out.println(candidate);
//        assertTrue(c.isPalindrome(candidate));
    }

}
