import ejercicio1.GestorLlamadas;

import java.util.Calendar;

public class GestorLlamadasStub extends GestorLlamadas {

    private int hora;

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public int getHoraActual() {
        return hora;
    }
}
