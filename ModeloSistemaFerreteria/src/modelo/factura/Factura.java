/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.factura;

import java.util.Date;
import java.util.LinkedList;
import modelo.personas.clientes.Cliente;
import modelo.personas.empleados.Empleado;

/**
 *
 * @author Michael Chen W.
 */
public class Factura {

    public Factura() {
        codigo = -1;
        cliente = null;
        vendedor = null;
        fecha = null;
        detalles = new LinkedList<>();
        pagado = false;
        despachado = false;
    }    
    public Factura(int codigo, Cliente cliente, Empleado vendedor) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.vendedor = vendedor;
        detalles = new LinkedList<>();
        fecha = new Date();
        pagado = false;
        despachado = false;
    }

    public Factura(Cliente cliente, Empleado vendedor, Date fecha) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fecha = fecha;
        codigo = -1;
        detalles = new LinkedList<>();
        pagado = false;
        despachado = false;
    }
    

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVendedor(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void agregarDetalle(LineaDetalle ld) {
        detalles.add(ld);
    }

    public void setDetalles(LinkedList<LineaDetalle> detalles) {
        this.detalles = detalles;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getVendedor() {
        return vendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public LinkedList<LineaDetalle> getDetalles() {
        return detalles;
    }

    public boolean isPagado() {
        return pagado;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public Double getTotal() {
        return 1000.234;    //THIS IS TEMPORARY
    }    
     public double calculateTotalPago() {
        double pag = 0;
        LinkedList<LineaDetalle> pagos = getDetalles();
        for (LineaDetalle detalle : pagos) {
            pag += (detalle.getCantidad() * detalle.getPrecio());
        }
        return pag;
    }

    int codigo; //autogenerado..
    Cliente cliente;
    Empleado vendedor;
    Date fecha;
    LinkedList<LineaDetalle> detalles;
    boolean pagado;
    boolean despachado;
}
