package ppss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
public class FicheroTextoTest2 {

    String nombreFichero;

    String esperado;
    int intResul;
    FicheroTexto ft;


    @BeforeEach
    public void setup(){
        ft = new FicheroTexto();
    }

    @Test
    public void contarCaracteresC1(){
        nombreFichero = "ficheroC1.txt";
        FicheroException fe = assertThrows(FicheroException.class ,() -> ft.contarCaracteres(nombreFichero));
        esperado = "ficheroC1.txt (No existe el archivo o el directorio)";

        assertEquals(esperado, fe.getMessage());
    }

    @Test
    public void contarCaracteresC2(){
        nombreFichero = "src/test/resources/ficheroCorrecto.txt";
        intResul = assertDoesNotThrow(() -> ft.contarCaracteres(nombreFichero));
        assertEquals(3, intResul);
    }


    @Test
    public void contarCaracteresC3(){
        fail();
    }

    @Test
    public void contarCaracteresC4(){
        fail();
    }
}
