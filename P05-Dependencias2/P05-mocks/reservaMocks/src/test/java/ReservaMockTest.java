import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.FactoriaBOs;
import ppss.IOperacionBO;
import ppss.Reserva;
import ppss.excepciones.*;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
public class ReservaMockTest {

    IMocksControl ctrl;
    FactoriaBOs f;
    IOperacionBO op;
    Reserva r;

    String login, password, socio, esperado;
    String[] isbns;


    @BeforeEach
    public void setup(){
        ctrl = createStrictControl();
        r = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").createMock(ctrl);
        op = ctrl.mock(IOperacionBO.class);
        f = ctrl.mock(FactoriaBOs.class);


    }
    @Test
    public void realizarReservaC1(){
        login = "xxxx";
        password = "xxxx";
        socio = "Pepe";
        isbns = new String[]{"33333"};
        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(false);

        ctrl.replay();
        esperado = "ERROR de permisos; ";

        ReservaException real = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, isbns));

        assertEquals(esperado, real.getMessage());

        ctrl.verify();
    }

    @Test public void realizarReservaC2(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111", "22222"};
        r.setFac(f);
        expect(r.compruebaPermisos(login,password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[0]));
        EasyMock.expectLastCall();
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[1]));
        EasyMock.expectLastCall();

        ctrl.replay();

        assertDoesNotThrow(() -> r.realizaReserva(login, password, socio, isbns));

        ctrl.verify();

    }
    @Test public void realizarReservaC3(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111", "22222", "55555"};
        r.setFac(f);
        expect(r.compruebaPermisos(login,password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[1]));
        expectLastCall();
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[2]));
        expectLastCall().andThrow(new IsbnInvalidoException());

        ctrl.replay();

        ReservaException resultado = assertThrows(ReservaException.class ,() -> r.realizaReserva(login, password, socio, isbns));

        assertEquals("ISBN invalido:11111; ISBN invalido:55555; ", resultado.getMessage());

        ctrl.verify();

    }
    @Test public void realizarReservaC4(){
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[]{"22222"};
        r.setFac(f);
        expect(r.compruebaPermisos(login,password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new SocioInvalidoException());

        ctrl.replay();

        ReservaException resultado = assertThrows(ReservaException.class ,() -> r.realizaReserva(login, password, socio, isbns));

        assertEquals("SOCIO invalido; ", resultado.getMessage());

        ctrl.verify();

    }

    @Test public void realizarReservaC5(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111", "22222", "33333"};
        r.setFac(f);
        expect(r.compruebaPermisos(login,password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[1]));
        expectLastCall();
        assertDoesNotThrow(()->op.operacionReserva(socio, isbns[2]));
        expectLastCall().andThrow(new JDBCException());

        ctrl.replay();

        ReservaException resultado = assertThrows(ReservaException.class ,() -> r.realizaReserva(login, password, socio, isbns));

        assertEquals("ISBN invalido:11111; CONEXION invalida; ", resultado.getMessage());

        ctrl.verify();

    }
}
