package ppss.ejercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest2 {

    GestorLlamadasStub2 g;

    @BeforeEach
    public void setup(){
        g = new GestorLlamadasStub2();
    }

    @Test
    public void calculaConsumoC1(){
        g.c.hora = 15;
        double resul = g.calculaConsumo(10);
        assertEquals(208, resul);
    }
    @Test
    public void calculaConsumoC2(){
        g.c.hora = 22;
        double resul = g.calculaConsumo(10);
        assertEquals(105, resul);
    }


}
