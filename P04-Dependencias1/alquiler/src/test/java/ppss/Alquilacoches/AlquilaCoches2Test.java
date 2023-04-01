package ppss.Alquilacoches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

public class AlquilaCoches2Test {

    float esperado;
    AlquilaCoche2Stub ac;
    Calendario2Stub c;
    ServicioStub2 s;

    @BeforeEach
    public void setup(){
        ac = new AlquilaCoche2Stub();
        c = new Calendario2Stub();
        s = new ServicioStub2();
    }

    @Test
    public void calculaPrecioC1(){

        s.setP(10);
        ac.setServicio(s);
        ac.calendario = c;
        esperado = 75;
        Ticket resul = assertDoesNotThrow(() -> ac.calculaPrecio(TipoCoche.TURISMO, LocalDate.of(2023, 05, 18),10));
        assertEquals(esperado, resul.getPrecio_final());
    }

    @Test
    public void calculaPrecioC2(){

        s.setP(10);
        ac.setServicio(s);
        c.addFestivo(LocalDate.of(2023,06,20));
        c.addFestivo(LocalDate.of(2023,06,24));
        ac.calendario = c;
        esperado = (float) 62.5;

        Ticket resul = assertDoesNotThrow(() -> ac.calculaPrecio(TipoCoche.CARAVANA, LocalDate.of(2023,06,19),7));

        assertEquals(esperado, resul.getPrecio_final());
    }


    @Test
    public void calculaPrecioC3(){
        s.setP(10);
        ac.setServicio(s);
        c.addExcepcion(LocalDate.of(2023,04,18));
        c.addExcepcion(LocalDate.of(2023,04,22));
        c.addExcepcion(LocalDate.of(2023,04,21));
        ac.calendario = c;
        String esperadoMensaje= "Error en dia: 2023-04-18; Error en dia: 2023-04-21; Error en dia: 2023-04-22; ";
        MensajeException resul = assertThrows(MensajeException.class, () -> ac.calculaPrecio(TipoCoche.TURISMO, LocalDate.of(2023,04,17), 8));
        assertEquals(esperadoMensaje, resul.getMessage());

    }

}
