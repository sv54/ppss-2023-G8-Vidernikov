import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.internal.MocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.Premio.ClienteWebService;
import ppss.Premio.ClienteWebServiceException;
import ppss.Premio.Premio;

import static org.junit.jupiter.api.Assertions.*;

import static org.easymock.EasyMock.*;
import java.util.Random;

public class PremioTest {

    IMocksControl ctrl;

    Premio p;
    ClienteWebService c;

    @BeforeEach
    public void setup(){
        ctrl = createStrictControl();
        p = partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        c = ctrl.mock(ClienteWebService.class);

    }

    @Test
    public void compruebaPremioC1(){
        p.cliente = c;
        expect(p.generaNumero()).andReturn((float) 0.07);
        assertDoesNotThrow(() -> expect(c.obtenerPremio()).andReturn("entrada final Champions"));

        ctrl.replay();
        String resul = p.compruebaPremio();
        assertEquals("Premiado con entrada final Champions", resul);
        ctrl.verify();
    }

    @Test
    public void compruebaPremioC2(){
        p.cliente = c;

        expect(p.generaNumero()).andReturn((float) 0.03);
        assertDoesNotThrow(() -> expect(c.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        ctrl.replay();
        String resul = p.compruebaPremio();
        assertEquals("No se ha podido obtener el premio", resul);
        ctrl.verify();
    }

    @Test
    public void compruebaPremioC3(){
        p.cliente = c;

        expect(p.generaNumero()).andReturn((float) 0.3);
        ctrl.replay();
        String resul = p.compruebaPremio();
        assertEquals("Sin premio", resul);
        ctrl.verify();
    }













/*
    IMocksControl ctrl;
    Random r;
    ClienteWebService cw;
    Premio p;


    @BeforeEach
    public void setup(){
        p = new Premio();
        ctrl = createStrictControl();
        r = ctrl.createMock(Random.class);
        cw = ctrl.createMock(ClienteWebService.class);

    }

    @Test
    public void compruebaPremioC1(){

        p.cliente = cw;
        p.generador = r;
        expect(r.nextFloat()).andReturn((float)0.07);
        assertDoesNotThrow(() -> expect(cw.obtenerPremio()).andReturn("entrada final Champions"));
        ctrl.replay();

        String esperado = "Premiado con entrada final Champions";
        String real = p.compruebaPremio();

        assertEquals(esperado, real);
        ctrl.verify();


    }

    @Test
    public void compruebaPremioC2(){
        p.cliente = cw;
        p.generador = r;
        expect(r.nextFloat()).andReturn((float) 0.03);
        assertDoesNotThrow(()-> expect(cw.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        ctrl.replay();

        String esperado = "No se ha podido obtener el premio";
        String real = p.compruebaPremio();

        ctrl.verify();
        assertEquals(esperado,real);

    }

    @Test
    public void compruebaPremioC3(){
        p.cliente = cw;
        p.generador = r;
        expect(r.nextFloat()).andReturn((float) 0.3);
        ctrl.replay();

        String esperado = "Sin premio";
        String real = p.compruebaPremio();
        assertEquals(esperado,real);

        ctrl.verify();
    }
    */

}
