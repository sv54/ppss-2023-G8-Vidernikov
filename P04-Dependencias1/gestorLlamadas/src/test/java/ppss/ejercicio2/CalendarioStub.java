package ppss.ejercicio2;

public class CalendarioStub extends Calendario{


    int h;
    @Override
    public int getHoraActual() {
        return h;
    }

    public void setHoraActual(int s){
        h = s;
    }
}
