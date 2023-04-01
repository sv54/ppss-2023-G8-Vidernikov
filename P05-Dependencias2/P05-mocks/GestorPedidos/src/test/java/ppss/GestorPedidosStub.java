package ppss;

public class GestorPedidosStub extends GestorPedidos {


    Buscador b = new BuscadorStub();

    @Override
    public Buscador getBuscador() {
        return b;
    }

    public void setB(Buscador b) {
        this.b = b;
    }
}
