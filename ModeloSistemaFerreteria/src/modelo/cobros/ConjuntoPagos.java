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
        String query = "INSERT INTO `ferreteriadatos`.`pago` (`Factura`, `TipoPago`, `Pago`,`CuentaCheque`,`CuentaTarjeta`) VALUES ('%d', '%d', '%f', '%d', '%d');";
        query = String.format(query, pag.getFactura().getCodigo(), pag.getTipoPago(), pag.getPago(), pag.getCounterCheque(), pag.getCounterTarjeta());
        int result = dbc.executeUpdate(query);
        if(result == 0)
            throw new Exception("Producto existente.");
    }
    
    public void updatePago(Pago pag) throws Exception{
        String query = "UPDATE `ferreteriadatos`.`pago` SET `TipoPago`='%d', `Pago`='%d', `CuentaCheque`='%d', `CuentaTarjeta`='%d' WHERE `Factura`='%d';";
        query = String.format(query, pag.getTipoPago(), pag.getPago(), pag.getFactura(), pag.getCounterCheque(), pag.getCounterTarjeta(), pag.getFactura().getCodigo());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Producto inexistente.");
        }
    }
    
    private Pago pago(ResultSet rs) {
        try {
            Pago pag = new Pago();
            
            int codFactura = rs.getInt("Factura");
            pag.setFactura(getFactura(codFactura));
            
            int tipoPago = rs.getInt("TipoPago");
            pag.setTipoPago(tipoPago);
            pag.setPago(rs.getDouble("Pago"));
            pag.setCounterTarjeta(rs.getInt("CounterTarjeta"));
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
