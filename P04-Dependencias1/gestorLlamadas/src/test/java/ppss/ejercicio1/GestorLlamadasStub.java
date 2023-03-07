package ppss.ejercicio1;

import ppss.ejercicio1.GestorLlamadas;

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
