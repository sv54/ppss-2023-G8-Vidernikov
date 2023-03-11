package ppss.Alquilacoches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class AlquilaCochesTest {

    AlquilaCochesDoble ac;
    TipoCoche tp;
    CalendarioDoble c;
    ServicioDoble s;


    @BeforeEach
    public void setup(){
        ac = new AlquilaCochesDoble();
        tp = null;
        c = new CalendarioDoble();
        s = new ServicioDoble();

    }


    @Tag("Ejercicio3")
    @Test
    public void calculaPrecioC1(){

        float esperado = 75;

        ac.calendario = c;
        s.setP(10);
        ac.setS(s);
        Ticket resultado = assertDoesNotThrow(() -> ac.calculaPrecio(TipoCoche.TURISMO, LocalDate.of(2023, 05, 18), 10));

        assertEquals(esperado, resultado.getPrecio_final());

    }

    @Tag("Ejercicio3")
    @Test
    public void calculaPrecioC2(){

        float esperado = (float) 62.5;
        c.addFestivo(LocalDate.of(2023,6,20));
        c.addFestivo(LocalDate.of(2023,6,24));
        ac.calendario = c;
        s.setP(10);

        ac.setS(s);
        Ticket resul = assertDoesNotThrow(() -> ac.calculaPrecio(TipoCoche.CARAVANA, LocalDate.of(2023,6,19), 7));

        assertEquals(esperado, resul.getPrecio_final());

    }

    @Tag("Ejercicio3")
    @Test
    public void calculaPrecioC3(){

        String esperado = "Error en dia: 2023-04-18; Error en dia: 2023-04-21; Error en dia: 2023-04-22; ";
        c.addExcepcion(LocalDate.of(2023,04,18));
        c.addExcepcion(LocalDate.of(2023,04,21));
        c.addExcepcion(LocalDate.of(2023,04,22));
        ac.calendario = c;

        s.setP(10);
        ac.setS(s);

        MensajeException resul = assertThrows(MensajeException.class, () -> ac.calculaPrecio(TipoCoche.TURISMO, LocalDate.of(2023,04,17), 8));

        assertEquals(esperado, resul.getMessage());

    }
}
