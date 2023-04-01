import ppss.Reserva2;
import ppss.Usuario;

public class ReservaDoble2 extends Reserva2 {

    boolean resul;
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return resul;
    }

    public void setResul(boolean b){
        resul = b;
    }
}



