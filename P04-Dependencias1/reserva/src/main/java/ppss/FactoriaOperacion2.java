package ppss;

public class FactoriaOperacion2 {
    static IOperacionBO op;

    public static IOperacionBO createOperacion(){
        if(op != null){
            return op;
        }
        else{
            return new Operacion();
        }
    }

    public static void setOp(IOperacionBO op1) {
        op = op1;
    }
}
