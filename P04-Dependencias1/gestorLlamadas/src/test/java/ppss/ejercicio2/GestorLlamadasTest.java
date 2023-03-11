package ppss.ejercicio2;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest {

    @Tag("Ejercicio2")

    @Test
    public void gestroLlamadasC1(){

        GestorLlamadasStub g = new GestorLlamadasStub();
        double resultadoEsperado = 208;
        g.setHoraCalendario(15);

        double resultadoReal = g.calculaConsumo(10);
        assertEquals(resultadoEsperado,resultadoReal);

    }

    @Tag("Ejercicio2")
    @Test
    public void gestroLlamadasC2(){

        GestorLlamadasStub g = new GestorLlamadasStub();
        double resultadoEsperado = 105;
        g.setHoraCalendario(22);

        double resultadoReal = g.calculaConsumo(10);
        assertEquals(resultadoEsperado,resultadoReal);

    }

}
