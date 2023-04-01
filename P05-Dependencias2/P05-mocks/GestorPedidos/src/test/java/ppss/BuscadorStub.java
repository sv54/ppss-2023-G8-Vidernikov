package ppss;

public class BuscadorStub extends Buscador {

    int elem = 0;
    @Override
    public int elemPendientes(Cliente cli) {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }
}
