package ppss.ejercicio2;

public class GestorLlamadasStub2 extends GestorLlamadas{

    public CalendarioStub2 c = new CalendarioStub2();
    @Override
    public Calendario getCalendario() {
        return c;
    }
}
