package ppss.Alquilacoches;

import ppss.Alquilacoches.IService;
import ppss.Alquilacoches.TipoCoche;

public class ServicioStub2 implements IService {


    float p;

    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return p;
    }

    public void setP(float p1){
        p = p1;
    }
}
