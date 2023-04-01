package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.Reserva;

import static org.junit.jupiter.api.Assertions.*;

import static org.easymock.EasyMock.*;

public class GestorPedidosMockTest {

    GestorPedidos gp;

    Buscador b;
    Cliente c;

    IMocksControl ctrl;


    @BeforeEach
    public void setup(){

        ctrl = createStrictControl();
        gp = partialMockBuilder(GestorPedidos.class).addMockedMethod("getBuscador").mock(ctrl);
        b = ctrl.mock(Buscador.class);
        c = new Cliente("cliente1", 20);
    }

    @Test
    public void generarFacturaC1(){

        expect(gp.getBuscador()).andReturn(b);
        expect(b.elemPendientes(c)).andReturn(10);

        ctrl.replay();


        Factura resul = Assertions.assertDoesNotThrow(() -> gp.generarFactura(c));
        Factura esperado = new Factura();
        esperado.setIdCliente("cliente1");
        esperado.setTotal_factura(200);
        Assertions.assertEquals(esperado, resul);

        ctrl.verify();
    }

    @Test
    public void generarFacturaC2(){

        expect(gp.getBuscador()).andReturn(b);
        expect(b.elemPendientes(c)).andReturn(0);

        ctrl.replay();


        FacturaException resul = assertThrows(FacturaException.class,() -> gp.generarFactura(c));

        Assertions.assertEquals("No hay nada pendiente de facturar", resul.getMessage());

        ctrl.verify();
    }




}
