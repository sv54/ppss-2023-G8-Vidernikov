package ppss;

public class GestorPedidos {

    public Buscador getBuscador() {
        Buscador busca = new Buscador();
        return busca;
    }


    public Factura generarFactura(Cliente cli)
            throws FacturaException {
        Factura factura = new Factura();
        Buscador buscarDatos = getBuscador();

        int numElems = buscarDatos.elemPendientes(cli);
        if (numElems>0) {
            //código para generar la factura
            factura.setIdCliente(cli.getIdCliente());
            float total = cli.getPrecioCliente()*numElems;
            factura.setTotal_factura(total);
        } else {
            throw new FacturaException("No hay nada pendiente de facturar");
        }
        return factura;
    }
}
