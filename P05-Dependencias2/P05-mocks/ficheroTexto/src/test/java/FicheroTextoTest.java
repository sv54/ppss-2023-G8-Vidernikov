import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.FicheroException;
import ppss.FicheroTexto;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.*;
public class FicheroTextoTest {

    IMocksControl ctrl;
    FicheroTexto ft;
    FileReader fr;

    @BeforeEach
    public void setup(){
        ctrl = EasyMock.createStrictControl();
        ft = partialMockBuilder(FicheroTexto.class).addMockedMethod("createFileReader").createMock(ctrl);
        fr = ctrl.createMock(FileReader.class);
    }

    @Test
    public void contarCaracteresC1(){
        assertDoesNotThrow(() -> expect(ft.createFileReader("src/test/resources/ficheroC1.txt")).andReturn(fr));
        assertDoesNotThrow(() -> expect(fr.read()).andReturn(1).times(2).andThrow(new IOException()));

        ctrl.replay();

        FicheroException fe = assertThrows(FicheroException.class, () -> ft.contarCaracteres("src/test/resources/ficheroC1.txt"));
        assertEquals("src/test/resources/ficheroC1.txt (Error al leer el archivo)", fe.getMessage());

        ctrl.verify();
    }

    @Test
    public void contarCaracteresC2(){
        assertDoesNotThrow(() -> expect(ft.createFileReader("src/test/resources/ficheroC2.txt")).andReturn(fr));
        assertDoesNotThrow(() -> expect(fr.read()).andReturn(1).times(3).andReturn(-1));
        assertDoesNotThrow(() -> fr.close()); expectLastCall().andThrow(new IOException());

        ctrl.replay();

        FicheroException fe = assertThrows(FicheroException.class, () -> ft.contarCaracteres("src/test/resources/ficheroC2.txt"));
        assertEquals( "src/test/resources/ficheroC2.txt (Error al cerrar el archivo)", fe.getMessage());

        ctrl.verify();
    }







}
