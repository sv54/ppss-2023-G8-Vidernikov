package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GestorPedidosStubsTest {

    GestorPedidosStub gp;
    BuscadorStub b;

    Factura esperado;
    Cliente c;

    @BeforeEach
    public void setup(){
        gp = new GestorPedidosStub();
        b = new BuscadorStub();
    }


    @Test
    public void generarFacturaC1(){
        b.setElem(10);
        c = new Cliente("cliente1", 20);
        gp.setB(b);

        esperado = new Factura();
        esperado.setIdCliente("cliente1");
        esperado.setTotal_factura(200);

        Factura resul = Assertions.assertDoesNotThrow(() -> gp.generarFactura(c));

        Assertions.assertEquals(esperado,resul);
    }

    @Test
    public void generarFacturaC2(){
        b.setElem(0);
        c = new Cliente("cliente1", 20);
        gp.setB(b);

        FacturaException resul = assertThrows(FacturaException.class, () -> gp.generarFactura(c));

        Assertions.assertEquals("No hay nada pendiente de facturar", resul.getMessage());
    }


}
