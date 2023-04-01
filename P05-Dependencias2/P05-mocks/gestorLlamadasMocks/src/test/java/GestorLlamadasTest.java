import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.Calendario;
import ppss.GestorLlamadas;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest {

    IMocksControl ctrl;
    GestorLlamadas gl;
    Calendario c;

    @BeforeEach
    public void setup(){
        ctrl = createStrictControl();
        gl = partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        c = ctrl.mock(Calendario.class);
    }

    @Test
    public void calculaConsumoC1(){
        expect(gl.getCalendario()).andReturn(c);
        expect(c.getHoraActual()).andReturn(10);

        ctrl.replay();

        double resul =  assertDoesNotThrow(() -> gl.calculaConsumo(22));
        assertEquals(457.6, resul);

    }

    @Test
    public void calculaConsumoC2(){
        expect(gl.getCalendario()).andReturn(c);
        expect(c.getHoraActual()).andReturn(21);

        ctrl.replay();

        double resul =  assertDoesNotThrow(() -> gl.calculaConsumo(13));
        assertEquals(136.5, resul);

    }









    /*
    IMocksControl ctrl;
    GestorLlamadas gl;
    Calendario c;

    @BeforeEach
    public void setup(){
        ctrl = EasyMock.createStrictControl();
        c = ctrl.createMock(Calendario.class);
        gl = partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock(ctrl);

    }

    @Test
    public void calculaConsumoC1(){
        expect(gl.getCalendario()).andReturn(c);
        expect(c.getHoraActual()).andReturn(10);
        ctrl.replay();
        double result = gl.calculaConsumo(22);
        double esperado = 457.6;

        assertEquals(esperado, result);

        EasyMock.verify(c, gl);
    }

    @Test
    public void calculaConsumoC2(){

        expect(gl.getCalendario()).andReturn(c);
        expect(c.getHoraActual()).andReturn(21);
        ctrl.replay();
        double result = gl.calculaConsumo(13);
        double esperado = 136.5;

        assertEquals(esperado, result);


    }

*/
}
