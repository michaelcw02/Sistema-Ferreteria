/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.inventarios;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ConjuntoInventarios(DataBaseConnection dbc) {
        this.dbc = dbc;
    }
    public LinkedList<Inventario> getAllInventario() {
        LinkedList<Inventario> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Inventario;";
            query = String.format(query);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next())
                listaResultado.add(inventario(rs));
        } catch (Exception e) {
        }
        return listaResultado;
    }
    public Inventario getInventario(Date date, String codigo) throws Exception{
        
        String query = "SELECT * " + "FROM Inventario WHERE Fecha = '%s'  AND Producto = '%s';";
        query = String.format(query, dateToSQL(date), codigo);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return inventario(rs);
        }
        else {
            throw new Exception("Inventario inexistente.");
        }
    }
    public LinkedList<Inventario> searchInventarioByProducto(String codigo) {
        LinkedList<Inventario> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Inventario WHERE Producto = '%s'";
            query = String.format(query, codigo);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(inventario(rs));
            }
        } catch (Exception ex) {
        }
        return listaResultado;
    }
    public LinkedList<Inventario> searchInventarioByDate(Date date) throws Exception{
        
        LinkedList<Inventario> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Inventario WHERE Nombre like '%%%s%%'";
            query = String.format(query, dateToSQL(date));
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(inventario(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deleteInventario(String idProducto) throws Exception{
        String query = "DELETE FROM Inventario WHERE Producto = '%s'";
        query = String.format(query, idProducto);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario inexistente.");
        }
    }
    public void addInventario(Inventario inv) throws Exception{
              
        String query = "INSERT INTO `ferreteriadatos`.`inventario` (`Fecha`, `Producto`, `Cantidad`, `isActivo`) VALUES ('%s', '%s', '%d', '%d');";
        query = String.format(query, dateToSQL(inv.getFecha()), inv.getProducto().getCodigo(), inv.getCantidad(), toInt(inv.isActivo()));
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario existente.");
        }
        
    }
    public void updateInventario(Inventario inv) throws Exception{
        
        String query = "UPDATE `ferreteriadatos`.`inventario` SET `Cantidad`='%d', `isActivo`='%d' WHERE `Fecha`='%s' and`Producto`='%s';";
        query = String.format(query, inv.getCantidad(), toInt(inv.isActivo()), dateToSQL(inv.getFecha()), inv.getProducto().getCodigo());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Inventario inexistente.");
        }
    }
    public int getSumOfProduct(String idProducto) {
        int cantidad = 0;
        try {
            String query = "SELECT SUM(Cantidad) AS CantidadTotal FROM `ferreteriadatos`.`inventario` WHERE Producto = '%s';";
            query = String.format(query, idProducto);
            ResultSet rs = dbc.executeQuery(query);
            if (rs.next()) {
                cantidad = rs.getInt("CantidadTotal");
            }
        } catch (Exception e) {
        }
        return cantidad;
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
    
    private String dateToSQL(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    private int toInt(boolean b) {
        return Boolean.compare(b, false);
    }
    
    DataBaseConnection dbc;
}
