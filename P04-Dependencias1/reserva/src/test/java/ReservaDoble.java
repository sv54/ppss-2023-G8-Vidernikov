import ppss.Reserva;
import ppss.Usuario;

public class ReservaDoble extends Reserva {

    boolean permisos;

    public void setPermisos(boolean p){
        permisos = p;
    }
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return permisos;
    }
}
