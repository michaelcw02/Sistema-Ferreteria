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

    public Pago() {
        factura = null;
        tipoPago = 0;
        pago = 0.0;
        counterTarjeta = null;
        counterCheque = null;
    }

    public Pago(Factura factura, int tipoPago, double pago, CounterTarjeta counterTarjeta, CounterCheque counterCheque) {
        this.factura = factura;
        this.tipoPago = tipoPago;
        this.pago = pago;
        this.counterTarjeta = counterTarjeta;
        this.counterCheque = counterCheque;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public void setCounterTarjeta(int counter) {
        counterTarjeta = new CounterTarjeta(counter);
    }

    public void setCounterCheque(int counter) {
        counterCheque = new CounterCheque(counter);
    }

    public Factura getFactura() {
        return factura;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public double getPago() {
        return pago;
    }
    
    public double getVuelto() {
        return pago - factura.getTotal();
    }

    public int getCounterTarjeta() {
        return counterTarjeta.getNumero();
    }

    public int getCounterCheque() {
        return counterCheque.getNumero();
    }
    
    
    public void imprimirPDF() {
        
    }    
    
    
    Factura factura;
    int tipoPago; //1: Efectivo, 2: Tarjeta, 3: Cheque.
    double pago;
    CounterTarjeta counterTarjeta;
    CounterCheque counterCheque;
}
