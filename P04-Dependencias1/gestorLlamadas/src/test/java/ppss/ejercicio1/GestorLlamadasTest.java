package ppss.ejercicio1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class GestorLlamadasTest {

    GestorLlamadasStub gl;

    @Tag("Ejercicio1")

    @Test
    public void calculaConsumoC1(){
        gl = new GestorLlamadasStub();
        gl.setHora(15);
        double resultadoReal = gl.calculaConsumo(10);
        double resultadoEsperado = 208;
        assertEquals(resultadoEsperado, resultadoReal);

    }

    @Tag("Ejercicio1")
    @Test
    public void calculaConsumoC2(){
        gl = new GestorLlamadasStub();
        gl.setHora(22);
        double resultadoReal = gl.calculaConsumo(10);
        double resultadoEsperado = 105;
        assertEquals(resultadoEsperado, resultadoReal);
    }
}
