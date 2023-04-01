package ppss.ejercicio1;

public class GestorLlamadasStub2 extends GestorLlamadas{

    private int hora;

    public void setHora(int h){
        hora = h;
    }

    @Override
    public int getHoraActual(){
        return hora;
    }
}
