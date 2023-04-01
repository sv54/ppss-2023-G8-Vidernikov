import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.FactoriaBOs;
import ppss.IOperacionBO;
import ppss.Reserva;
import ppss.excepciones.*;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class ReservaStubTest {

    Reserva r;

    FactoriaBOs f;

    IOperacionBO op;

    String esperado, login, password, socio;


    @BeforeEach
    public void setup(){
        r = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").niceMock();
        f = niceMock(FactoriaBOs.class);
        op = niceMock(IOperacionBO.class);
    }

    @Test
    public void realizarReservaC1(){
        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(false);

        replay(r);

        ReservaException resul = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, new String[]{"33333"}));
        assertEquals("ERROR de permisos; ", resul.getMessage());

    }

    @Test
    public void realizarReservaC2(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andStubReturn(op);
        assertDoesNotThrow(() -> op.operacionReserva(anyString(), anyString() ));

        replay(r, f, op);
        r.setFd(f);
        assertDoesNotThrow(() -> r.realizaReserva(login, password, socio, new String[]{"22222","33333"}));
    }

    @Test
    public void realizarReservaC3(){
        login = "ppss";
        password = "ppss";
        String[] isbns = new String[]{"11111","22222", "55555"};
        socio = "Pepe";
        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andStubReturn(op);
        assertDoesNotThrow(() -> op.operacionReserva(socio, isbns[1]));
        assertDoesNotThrow(() -> op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(() -> op.operacionReserva(socio, isbns[2]));
        expectLastCall().andThrow(new IsbnInvalidoException());

        replay(r, f, op);
        r.setFd(f);
        ReservaException resul = assertThrows(ReservaException.class,() -> r.realizaReserva(login, password, socio, isbns));
        assertEquals("ISBN invalido:11111; ISBN invalido:55555; ", resul.getMessage());
    }















    /*
    Reserva r;
    IOperacionBO op;
    FactoriaBOs f;
    String login, password, esperado, socio;

    String[] isbns;

    @BeforeEach
    public void setup(){
        r = createMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").niceMock();
        op = niceMock(IOperacionBO.class);
        f = niceMock(FactoriaBOs.class);

    }

    @Test
    public void realizarReservaC1(){
        login = "xxxx";
        password = "xxxx";
        socio = "Pepe";
        String[] isbn ={"33333"};
        esperado = "ERROR de permisos; ";
        r.setFac(f);

        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(false);
        replay(r, f, op);

        ReservaException resultado = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, isbn));
        assertEquals(esperado, resultado.getMessage());
    }

    @Test
    public void realizarReservaC2(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222" , "33333"};
        r.setFac(f);

        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[0]));
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[1]));

        replay(r, f, op);

        assertDoesNotThrow(() -> r.realizaReserva(login, password, socio, isbns));
    }

    @Test
    public void realizarReservaC3(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111", "22222" , "55555"};
        esperado = "ISBN invalido:11111; ISBN invalido:55555; ";
        r.setFac(f);

        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[1]));
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[2]));
        expectLastCall().andThrow(new IsbnInvalidoException());

        replay(r, f, op);

        ReservaException resultado = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, isbns));

        assertEquals(esperado, resultado.getMessage());
    }

    @Test
    public void realizarReservaC4(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222" };
        esperado = "SOCIO invalido; " ;
        r.setFac(f);

        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new SocioInvalidoException());

        replay(r, f, op);

        ReservaException resultado = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, isbns));

        assertEquals(esperado, resultado.getMessage());
    }

    @Test
    public void realizarReservaC5(){
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111" ,"22222", "33333" };
        esperado = "ISBN invalido:11111; CONEXION invalida; " ;
        r.setFac(f);

        expect(r.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andStubReturn(true);
        expect(f.getOperacionBO()).andReturn(op);
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[0]));
        expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[1]));
        assertDoesNotThrow( () -> op.operacionReserva(socio, isbns[2]));
        expectLastCall().andThrow(new JDBCException());

        replay(r, f, op);

        ReservaException resultado = assertThrows(ReservaException.class, () -> r.realizaReserva(login, password, socio, isbns));

        assertEquals(esperado, resultado.getMessage());
    }



*/
}
