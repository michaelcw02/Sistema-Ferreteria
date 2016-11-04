/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

import java.util.LinkedList;
import modelo.factura.Factura;
import modelo.factura.LineaDetalle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

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
        Document documento = new Document();
        FileOutputStream ficheroPdf;
        try {
            String userhome = System.getProperty("user.home");
            ficheroPdf = new FileOutputStream(userhome+"/Desktop/ejemplo.PDF");
            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        try {
            documento.open();
            Paragraph parrafo1 = new Paragraph("FERRETERIA",FontFactory.getFont("arial",24,Font.BOLD));                           
            documento.add(parrafo1);
            parrafo1.setAlignment(Chunk.ALIGN_MIDDLE);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            Paragraph parrafo4 = new Paragraph("FECHA: "+factura.getFecha(),FontFactory.getFont("arial",12));
            documento.add(parrafo4);
            Paragraph parrafo2 = new Paragraph("VENDEDOR: "+factura.getVendedor().getNombre(),FontFactory.getFont("arial",12));
            documento.add(parrafo2);
            Paragraph parrafo3 = new Paragraph("CLIENTE: "+factura.getCliente().getNombre(),FontFactory.getFont("arial",12));
            documento.add(parrafo3);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            PdfPTable tabla = new PdfPTable(5);
            //el numero indica la cantidad de Columnas
            tabla.addCell("Código");
            tabla.addCell("Descripción");
            tabla.addCell("Cantidad");
            tabla.addCell("Unidad");
            tabla.addCell("Precio");
            for(LineaDetalle linea : factura.getDetalles()){
            tabla.addCell(linea.getProducto().getCodigo());
            tabla.addCell(linea.getProducto().getDescripcion());
            tabla.addCell(Integer.toString(linea.getCantidad()));
            tabla.addCell(linea.getProducto().getUnidadMedida());
            tabla.addCell(String.valueOf(linea.getProducto().getPrecio()));
            }
            // esto nos crea una tabla de 3 Columnas por dos Filas
            documento.add(tabla);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("SUBTOTAL A PAGAR: "+String.valueOf(factura.subTotal())));
            documento.add(new Paragraph("DESCUENTO: "+String.valueOf(factura.getDescuento())));
            documento.add(new Paragraph("TOTAL A PAGAR: "+String.valueOf(factura.calculateTotalPago())));
              
           
            documento.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    Factura factura;
    int tipoPago; //1: Efectivo, 2: Tarjeta, 3: Cheque.
    double pago;
    int counterTarjeta;
    int counterCheque;
}
