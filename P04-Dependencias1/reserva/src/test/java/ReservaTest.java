import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ppss.FactoriaOperacion;
import ppss.Operacion;
import ppss.Reserva;
import ppss.excepciones.ReservaException;

public class ReservaTest {

    ReservaDoble rd;
    String esperado;


    @BeforeEach
    public void setup(){
        rd = new ReservaDoble();
        rd.setPermisos(true);
        FactoriaOperacion.setOperacion(null);
    }

    @Tag("Ejercicio4")
    @Test
    public void reservaC1(){

        rd.setPermisos(false);
        esperado = "ERROR de permisos; ";

        ReservaException result = assertThrows(ReservaException.class, () -> rd.realizaReserva("xxxx", "xxxx", "Luis", new String[]{"11111"}));

        assertEquals(esperado, result.getMessage());


    }


    @Tag("Ejercicio4")
    @Test
    public void reservaC2(){

        OperacionDoble op = new OperacionDoble();
        FactoriaOperacion.setOperacion(op);

        assertDoesNotThrow(() -> rd.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111", "22222"}));


    }
    @Tag("Ejercicio4")
    @Test
    public void reservaC3(){

        esperado = "ISBN invalido:33333; ISBN invalido:44444; ";
        OperacionDoble op = new OperacionDoble();
        FactoriaOperacion.setOperacion(op);

        ReservaException result = assertThrows(ReservaException.class, () -> rd.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111", "33333", "44444"}));

        assertEquals(esperado, result.getMessage());
    }

    @Tag("Ejercicio4")
    @Test
    public void reservaC4(){

        esperado = "SOCIO invalido; ";
        OperacionDoble op = new OperacionDoble();
        FactoriaOperacion.setOperacion(op);

        ReservaException result = assertThrows(ReservaException.class, () -> rd.realizaReserva("ppss", "ppss", "Pepe", new String[]{"11111"}));

        assertEquals(esperado, result.getMessage());
    }

    @Tag("Ejercicio4")
    @Test
    public void reservaC5(){

        esperado = "CONEXION invalida; ";
        OperacionDoble op = new OperacionDoble();
        op.addFallo("22222");
        FactoriaOperacion.setOperacion(op);

        ReservaException result = assertThrows(ReservaException.class, () -> rd.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111", "22222"}));

        assertEquals(esperado, result.getMessage());
    }

}
