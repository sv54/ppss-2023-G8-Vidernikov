import ppss.IOperacionBO;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class OperacionDoble implements IOperacionBO {

    String socioValido = "Luis";
    ArrayList<String> isbns = new ArrayList<String>(Arrays.asList("11111", "22222"));
    ArrayList<String> fallo = new ArrayList<String>();

    public void addFallo(String isbn){
        fallo.add(isbn);
    }


    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {

        if(socio != socioValido){
            throw new SocioInvalidoException();
        }
        if(fallo.contains(isbn)){
            throw new JDBCException();
        }
        if(!isbns.contains(isbn)){
            throw new IsbnInvalidoException();
        }


    }
}
