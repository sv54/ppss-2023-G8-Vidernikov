package ppss.Alquilacoches;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioDoble extends Calendario{

    ArrayList<LocalDate> festivos = new ArrayList<LocalDate>();
    ArrayList<LocalDate> excepcion = new ArrayList<LocalDate>();

    public void addFestivo(LocalDate festivo){
        festivos.add(festivo);
    }

    public void addExcepcion(LocalDate exception){
        excepcion.add(exception);
    }

    @Override
    public boolean es_festivo(LocalDate otroDia) throws CalendarioException {
        if(festivos.contains(otroDia)){
            return true;
        }
        else if(excepcion.contains(otroDia)){
            throw new CalendarioException();
        }
        return false;
    }
}
