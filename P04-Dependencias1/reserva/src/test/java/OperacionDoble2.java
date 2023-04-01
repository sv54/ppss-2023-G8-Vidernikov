import ppss.Operacion;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.ArrayList;
import java.util.Arrays;

public class OperacionDoble2 extends Operacion {

    String socioValido = "Luis";
    String isbnConexionInvalida;
    ArrayList<String> isbnInvalido = new ArrayList<String>(Arrays.asList("11111", "22222"));


    public void setIsbnConexionInvalida(String isbn){
        isbnConexionInvalida = isbn;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(socioValido != socio){
            throw new SocioInvalidoException();
        }
        if(isbnConexionInvalida == isbn){
            throw new JDBCException();
        }
        if(!isbnInvalido.contains(isbn)){
            throw new IsbnInvalidoException();
        }
    }
}
