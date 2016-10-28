/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.factura;

import java.util.LinkedList;
import modelo.personas.clientes.Cliente;
import modelo.personas.empleados.Empleado;
import org.joda.time.DateTime;

/**
 *
 * @author Michael Chen W.
 */
public class Factura {

    public Factura(int codigo, Cliente cliente, Empleado vendedor) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.vendedor = vendedor;
        detalles = new LinkedList<>();
        fecha = new DateTime();
        pagado = false;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public void setActivo(boolean activo) {
        this.pagado = activo;
    }
    
    //BLABLABLA 
    

    //THERE IS STILL MISSING ALL THE METHODS THAT A FACTURA HAS TO DO.

    
    
    
    int codigo; //autogenerado..
    Cliente cliente;
    Empleado vendedor;
    DateTime fecha;
    LinkedList<LineaDetalle> detalles;
    double subtotal;
    double impuesto;
    double descuento;
    double total;
    boolean pagado;
}
