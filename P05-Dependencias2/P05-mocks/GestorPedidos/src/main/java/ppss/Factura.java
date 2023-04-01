package ppss;

public class Factura {
    private String idCliente;
    private float total_factura;

    public Factura() { }

    public Factura(String idCliente) {
        this.idCliente = idCliente;
        this.total_factura = 0.0f;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public float getTotal_factura() {
        return total_factura;
    }

    public void setTotal_factura(float total_factura) {
        this.total_factura = total_factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factura that = (Factura) o;
        if (idCliente!= that.getIdCliente()) { return false; }
        if (total_factura!= that.getTotal_factura()) { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        return idCliente != null ? idCliente.hashCode() : 0;
    }
}