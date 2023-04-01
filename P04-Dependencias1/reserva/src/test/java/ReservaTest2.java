import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ppss.FactoriaOperacion2;
import ppss.Operacion;
import ppss.Reserva2;
import ppss.excepciones.ReservaException;

public class ReservaTest2 {

    ReservaDoble2 r;
    OperacionDoble2 op;
    String login, password,socio;

    String[] isbns;




    @BeforeEach
    public void setup(){
        r = new ReservaDoble2();
        op = new OperacionDoble2();
        FactoriaOperacion2.setOp(null);
    }

    @Test
    public void realizaReservaC1(){
        r.setResul(false);
        login = "xxxx";
        password = "xxxx";
        socio = "Luis";
        isbns = new String[]{"11111"};


        ReservaException resul = assertThrows(ReservaException.class,() -> r.realizaReserva(login, password, socio,isbns));
        assertEquals("ERROR de permisos; ", resul.getMessage());
    }
    @Test
    public void realizaReservaC2(){
        r.setResul(true);
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        FactoriaOperacion2.setOp(op);
        isbns = new String[]{"11111", "22222"};


        assertDoesNotThrow(() -> r.realizaReserva(login, password, socio,isbns));
    }


    @Test
    public void realizaReservaC3(){
        r.setResul(true);
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        FactoriaOperacion2.setOp(op);
        isbns = new String[]{"11111","33333","44444"};


        ReservaException resul = assertThrows(ReservaException.class,() -> r.realizaReserva(login, password, socio,isbns));
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", resul.getMessage());
    }

    @Test
    public void realizaReservaC4(){
        r.setResul(true);
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        FactoriaOperacion2.setOp(op);
        isbns = new String[]{"11111"};


        ReservaException resul = assertThrows(ReservaException.class,() -> r.realizaReserva(login, password, socio,isbns));
        assertEquals("SOCIO invalido; ", resul.getMessage());
    }

    @Test
    public void realizaReservaC5(){
        r.setResul(true);
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        op.setIsbnConexionInvalida("22222");
        FactoriaOperacion2.setOp(op);
        isbns = new String[]{"11111", "22222"};


        ReservaException resul = assertThrows(ReservaException.class,() -> r.realizaReserva(login, password, socio,isbns));
        assertEquals("CONEXION invalida; ", resul.getMessage());
    }



}
