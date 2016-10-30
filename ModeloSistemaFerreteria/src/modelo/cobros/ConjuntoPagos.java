/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;
import modelo.factura.*;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoPagos {

    public ConjuntoPagos() {
        dbc = new DataBaseConnection();
    }
    public ConjuntoPagos(DataBaseConnection dbc) {
        this.dbc = dbc;
    }

    public Pago getPagoByFactura(int cod) throws Exception{
        String query = "SELECT * " + "FROM Pago WHERE Factura = '%d'";
        query = String.format(query, cod);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return pago(rs);
        }
        else {
            throw new Exception("Pago inexistente.");
        }
    }
    public LinkedList<Pago> searchPagoByTipoPago(int tipo) throws Exception{
        LinkedList<Pago> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Pago WHERE TipoPago like '%%%d%%'";
            query = String.format(query, tipo);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(pago(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deletePago(int cod) throws Exception{
        String query = "DELETE FROM Pago, CuentaTarjeta, CuentaCheque WHERE Factura = '%d'";
        query = String.format(query, cod);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Producto inexistente.");
        }
    }
    public void addPago(Pago pag) throws Exception {
        String query = "INSERT INTO Producto (Factura, TipoPago, Pago)"
                    + "VALUES('%d', '%d', '%d')";
        query = String.format(query, pag.getFactura().getCodigo(), pag.getTipoPago(), pag.getPago());
        int result = dbc.executeUpdate(query);
        if(result != 0) {
            if(pag.getTipoPago() == 2)
                addPagoTarjeta(pag);
            if(pag.getTipoPago() == 3)
                addPagoCheque(pag);
        }
        else
            throw new Exception("Producto existente.");
    }
    public void addPagoTarjeta(Pago pag) throws Exception {
        String query = "INSERT INTO PagoTarjeta (Factura)" + "VALUES ('%d')";
        query = String.format(query, pag.getFactura());
        int result = dbc.executeUpdate(query);
        if(result == 0)
            throw new Exception("Pago existente.");
    } 
    public void addPagoCheque(Pago pag) throws Exception {
        String query = "INSERT INTO PagoCheque (Factura)" + "VALUES ('%d')";
        query = String.format(query, pag.getFactura());
        int result = dbc.executeUpdate(query);
        if(result == 0)
            throw new Exception("Pago existente.");
    }    
    
    public void updatePago(Pago pag) throws Exception{
        String query = "UPDATE Pago SET TipoPago = '%d', Pago = '%d'" + 
                        "WHERE Factura = '%d'";
        query = String.format(query, pag.getTipoPago(), pag.getPago(), pag.getFactura());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Producto inexistente.");
        }
    }
    public void updatePagoTarjeta(Pago pag) throws Exception {
        String query = "UPDATE CounterTarjeta SET Numero = '%d'" + 
                        "WHERE Factura = '%d'";
        query = String.format(query, pag.getCounterTarjeta());
        int result = dbc.executeUpdate(query);
        if(result == 0)
            throw new Exception("Pago existente.");
    } 
    public void updatePagoCheque(Pago pag) throws Exception {
        String query = "UPDATE CounterCheque SET Numero = '%d'" + 
                        "WHERE Factura = '%d'";
        query = String.format(query, pag.getCounterCheque());
        int result = dbc.executeUpdate(query);
        if(result == 0)
            throw new Exception("Pago existente.");
    }
    
    private Pago pago(ResultSet rs) {
        try {
            Pago pag = new Pago();
            
            int codFactura = rs.getInt("Factura");
            pag.setFactura(getFactura(codFactura));
            
            int tipoPago = rs.getInt("TipoPago");
            pag.setTipoPago(tipoPago);
            pag.setPago(rs.getDouble("Pago"));
            
            if(tipoPago == 2)
                pag.setCounterTarjeta(rs.getInt("CounterTarjeta"));
            if(tipoPago == 3)
                pag.setCounterCheque(rs.getInt("CounterCheque"));
            
            return pag;            
        } catch (Exception ex) {
            return null;
        }
    }    
    public Factura getFactura(int cod) throws Exception {
        ConjuntoFacturas cp = new ConjuntoFacturas(dbc);
        return cp.getFacturaByCod(cod);
    }
   
    DataBaseConnection dbc;
}
