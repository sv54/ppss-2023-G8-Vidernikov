package ppss;

public class Cliente {
    private String idCliente;
    private float precioCliente;

    public Cliente(String idCliente,float precioC) {
        this.idCliente = idCliente;
        this.precioCliente = precioC;
    }

    public float getPrecioCliente() {
        return precioCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setPrecioCliente(float precioCliente) {
        this.precioCliente = precioCliente;
    }
    //getters y setters
}