/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.inventarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;
import modelo.productos.ConjuntoProductos;
import modelo.productos.Producto;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoInventarios {

    public ConjuntoInventarios() {
        dbc = new DataBaseConnection();
    }
    
    public Inventario getInventario(String codigo, Date fecha) throws Exception{
        
        fecha = getTimeFormat(fecha);
        
        String query = "SELECT * " + "FROM Cliente WHERE Cedula = '%s' AND Fecha = '%s'";
        query = String.format(query, codigo, fecha);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return inventario(rs);
        }
        else {
            throw new Exception("Inventario inexistente.");
        }
    }
    public LinkedList<Inventario> searchInventarioByProducto(String codigo) throws Exception{
        LinkedList<Inventario> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Inventario WHERE Producto like '%%%s%%'";
            query = String.format(query, codigo);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(inventario(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public LinkedList<Inventario> searchInventarioByDate(Date fecha) throws Exception{
        
        fecha = getTimeFormat(fecha);
        
        LinkedList<Inventario> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Inventario WHERE Nombre like '%%%s%%'";
            query = String.format(query, fecha);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(inventario(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deleteInventario(String id) throws Exception{
        String query = "DELETE " + "FROM Inventario WHERE Cedula = '%s'";
        query = String.format(query, id);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario inexistente.");
        }
    }
    public void addInventario(Inventario inv) throws Exception{
        
        Date fecha = getTimeFormat(inv.getFecha());
        
        String query = "INSERT INTO Inventario (Fecha, Producto, Cantidad, isActivo)"
                    + "VALUES('%s', '%s', '%d', '%b')";
        query = String.format(query, fecha, inv.getProducto(), inv.getCantidad(), inv.isActivo());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario existente.");
        }
        
    }
    public void updateCliente(Inventario inv) throws Exception{
        
        Date fecha = getTimeFormat(inv.getFecha());
        
        String query = "UPDATE Inventario SET Cantidad = '%d', isActivo = '%b'" + 
                        "WHERE Fecha = '%s' AND Producto = '%s'";
        query = String.format(query, inv.getCantidad(), inv.isActivo(),
                        fecha, inv.getProducto());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario inexistente.");
        }
    }
    
    private Inventario inventario(ResultSet rs) throws Exception {
        try {
            Inventario inv = new Inventario();
            inv.setFecha(rs.getDate("Fecha"));
            String codProducto = rs.getString("Producto");
            inv.setProducto(getProducto(codProducto));
            inv.setCantidad(rs.getInt("Cantidad"));
            inv.setActivo(rs.getBoolean("isActivo"));
            return inv;            
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Producto getProducto(String cod) throws Exception {
        ConjuntoProductos cp = new ConjuntoProductos(dbc);
        return cp.getProductoByCod(cod);
    }
    
    private Date getTimeFormat(Date fecha) {
        try {
        SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
        return dt.parse(fecha.toString());        
        } catch(Exception e) {
            return new Date(1999, 12, 29);
        }
    }
    
    DataBaseConnection dbc;
}
