package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FicheroTest {

    String nombreFichero;
    FicheroTexto ficheroTexto;

    @BeforeEach
    public void setup(){
        nombreFichero = "";
        ficheroTexto = new FicheroTexto();
    }


    @Test
    public void contarCaracteresC1(){
        nombreFichero = "ficheroC1.txt";

        //assertThrows(class );
    }
}
