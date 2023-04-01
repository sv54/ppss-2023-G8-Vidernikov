package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.easymock.EasyMock.*;
public class GestorPedidoEstadoTest {

    GestorPedidos gp;
    Cliente c;
    Buscador bu;

    @BeforeEach
    public void setup(){
        c = new Cliente("cliente1", 20);
        bu = niceMock(Buscador.class);
        gp = partialMockBuilder(GestorPedidos.class).addMockedMethod("getBuscador").niceMock();

    }

    @Test
    public void generarFacturaC1(){
        expect(gp.getBuscador()).andStubReturn(bu);
        expect(bu.elemPendientes(anyObject())).andStubReturn(10);

        replay(gp, bu);

        Factura result = Assertions.assertDoesNotThrow(() -> gp.generarFactura(c));
        Factura esperado = new Factura();
        esperado.setTotal_factura(200);
        esperado.setIdCliente("cliente1");
        Assertions.assertEquals(esperado, result);
    }


    @Test
    public void generarFacturaC2(){
        expect(gp.getBuscador()).andStubReturn(bu);
        expect(bu.elemPendientes(anyObject())).andStubReturn(0);

        replay(gp, bu);

        FacturaException resul = assertThrows(FacturaException.class, () -> gp.generarFactura(c));

        assertEquals("No hay nada pendiente de facturar", resul.getMessage());

    }


}
