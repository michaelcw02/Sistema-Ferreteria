/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

import modelo.factura.Factura;

/**
 *
 * @author Michael Chen W.
 */
public class Pago {

    public Pago(Factura factura, int tipoPago, double pago) {
        this.factura = factura;
        this.tipoPago = tipoPago;
        this.pago = pago;
    }
    
    public double getVuelto() {
        return pago - factura.getTotal();
    }
    
    public void imprimirPDF() {
        
    }    
    
    
    Factura factura;
    int tipoPago; //1: Efectivo, 2: Tarjeta, 3: Cheque.
    double pago;
}
