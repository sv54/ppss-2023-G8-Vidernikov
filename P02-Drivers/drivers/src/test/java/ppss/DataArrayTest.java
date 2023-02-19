package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataArrayTest {

    DataArray dataArray;
    int borrar;

    @Test
    public void dataArrayTestC1(){
        borrar = 5;
        dataArray = new DataArray(new int[]{1,3,5,7});
        assertAll(
                () -> assertDoesNotThrow(() -> dataArray.delete(borrar)),
                () -> assertArrayEquals(new int[]{1,3,7}, dataArray.getColeccion())
        );
    }

    @Test
    public void dataArrayTestC2(){
        borrar = 3;
        dataArray = new DataArray(new int[]{1,3,3,5,7});
        assertAll(
                () -> assertDoesNotThrow(() -> dataArray.delete(borrar)),
                () -> assertArrayEquals(new int[]{1,3,5,7}, dataArray.getColeccion())
        );
    }

    @Test
    public void dataArrayTestC3(){
        borrar = 4;
        dataArray = new DataArray(new int[]{1,2,3,4,5,6,7,8,9,10});
        assertAll(
                () -> assertDoesNotThrow(() -> dataArray.delete(borrar)),
                () -> assertArrayEquals(new int[]{1,2,3,5,6,7,8,9,10}, dataArray.getColeccion())
        );
    }

    @Test
    public void dataArrayTestC4(){
        borrar = 8;
        dataArray = new DataArray(new int[]{});
        DataException dataException = assertThrows(DataException.class, () -> dataArray.delete(borrar));
        assertEquals( "No hay elementos en la colección", dataException.getMessage());
    }

    @Test
    public void dataArrayTestC5(){
        borrar = -5;
        dataArray = new DataArray(new int[]{1,3,5,7});
        DataException dataException = assertThrows(DataException.class, () -> dataArray.delete(borrar));
        assertEquals("El valor a borrar debe ser > 0", dataException.getMessage());
    }

    @Test
    public void dataArrayTestC6(){
        borrar = 0;
        dataArray = new DataArray(new int[]{});
        DataException dataException = assertThrows(DataException.class, () -> dataArray.delete(borrar));
        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", dataException.getMessage());
    }

    @Test
    public void dataArrayTestC7(){
        borrar = 8;
        dataArray = new DataArray(new int[]{1,3,5,7});
        DataException dataException = assertThrows(DataException.class, () -> dataArray.delete(borrar));
        assertEquals("Elemento no encontrado", dataException.getMessage());
    }

    @Tag("parametrizado")
    @Tag("conExcepciones")
    @ParameterizedTest
    @MethodSource("casosExcepciones")
    public void dataArrayTestC8(String mensajeEsperado, int borrar,int[] coleccion ){
        dataArray = new DataArray(coleccion);
        DataException dataException = assertThrows(DataException.class, () -> dataArray.delete(borrar));
        assertEquals(mensajeEsperado, dataException.getMessage());
    }

    public static Stream<Arguments> casosExcepciones(){
        return Stream.of(
                Arguments.of("No hay elementos en la colección",8,new int[]{}),
                Arguments.of("El valor a borrar debe ser > 0",-5, new int[]{1,3,5,7}),
                Arguments.of("Colección vacía. Y el valor a borrar debe ser > 0",0, new int[]{}),
                Arguments.of("Elemento no encontrado",8, new int[]{1,3,5,7})
        );
    }

}
