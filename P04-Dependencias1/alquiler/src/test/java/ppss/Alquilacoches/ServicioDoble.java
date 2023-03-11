package ppss.Alquilacoches;

public class ServicioDoble implements IService{

    float p;

    public void setP(float precio){
        p = precio;
    }

    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return p;
    }
}
