package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {

    String nombreFichero;
    FicheroTexto ficheroTexto;

    @BeforeEach
    public void setup() {
        nombreFichero = "";
        ficheroTexto = new FicheroTexto();
    }


    @Test
    public void contarCaracteresC1() {
        nombreFichero = "ficheroC1.txt";

        assertThrows(FicheroException.class, () -> ficheroTexto.contarCaracteres(nombreFichero));
    }

    @Test
    public void contarCaracteresC2() throws FicheroException {
        nombreFichero = "src/test/resources/ficheroCorrecto.txt";

        int resultadoEsperado = 3;
        assertEquals(resultadoEsperado, ficheroTexto.contarCaracteres(nombreFichero));
    }

    @Tag("excluido")
    @Test
    public void contarCaracteresC3() {
        fail();
    }

    @Tag("excluido")
    @Test
    public void contarCaracteresC4() {
        fail();
    }
}
