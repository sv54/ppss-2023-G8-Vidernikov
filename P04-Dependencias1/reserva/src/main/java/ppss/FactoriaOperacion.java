package ppss;

public class FactoriaOperacion {
    private static IOperacionBO op = null;

    public static void setOperacion(IOperacionBO ope){
        op = ope;
    }

    public static IOperacionBO create(){
        if(op != null) {
            return op;
        }
        return new Operacion();
    }


}
