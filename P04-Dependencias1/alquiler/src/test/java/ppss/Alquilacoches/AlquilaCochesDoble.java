package ppss.Alquilacoches;

public class AlquilaCochesDoble extends AlquilaCoches{

    private IService s;

    public void setS(IService ss){
        s = ss;
    }

    @Override
    public IService getServicio() {
        return s;
    }
}
