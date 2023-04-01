package ppss.Alquilacoches;

import ppss.Alquilacoches.Calendario;
import ppss.Alquilacoches.CalendarioException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calendario2Stub extends Calendario {

    ArrayList<LocalDate> festivo = new ArrayList<LocalDate>();

    ArrayList<LocalDate> excepciones = new ArrayList<LocalDate>();

    public void addFestivo(LocalDate dia){
        festivo.add(dia);
    }

    public void addExcepcion(LocalDate dia){
        excepciones.add(dia);
    }

    @Override
    public boolean es_festivo( LocalDate dia) throws CalendarioException {

        if(festivo.contains(dia)){
            return true;
        }
        if(excepciones.contains(dia)){
            throw new CalendarioException();
        }
        return false;
    }


}
