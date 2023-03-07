package ppss.ejercicio2;

public class GestorLlamadasStub extends GestorLlamadas{

    CalendarioStub c = new CalendarioStub();
    @Override
    public Calendario getCalendario(){
        return c;
    }

    public void setHoraCalendario(int h){
        c.setHoraActual(h);
    }
}
