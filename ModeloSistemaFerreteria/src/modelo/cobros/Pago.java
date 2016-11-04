/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

import java.util.LinkedList;
import modelo.factura.Factura;
import modelo.factura.LineaDetalle;

/**
 *
 * @author Michael Chen W.
 */
public class Pago {

    public Pago() {
        factura = null;
        tipoPago = 0;
        pago = 0.0;
        counterTarjeta = -1;
        counterCheque = -1;
    }

    public Pago(Factura factura, int tipoPago, double pago, int counterTarjeta, int counterCheque) {
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
        counterTarjeta = counter;
    }

    public void setCounterCheque(int counter) {
        counterCheque = counter;
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
        return counterTarjeta;
    }

    public int getCounterCheque() {
        return counterCheque;
    }

   
    public void imprimirPDF() {

    }

    Factura factura;
    int tipoPago; //1: Efectivo, 2: Tarjeta, 3: Cheque.
    double pago;
    int counterTarjeta;
    int counterCheque;
}
