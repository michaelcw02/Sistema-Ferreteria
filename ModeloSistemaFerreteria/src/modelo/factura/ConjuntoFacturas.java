/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.factura;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;
import modelo.personas.clientes.*;
import modelo.personas.empleados.*;
import modelo.productos.*;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoFacturas {

    public ConjuntoFacturas() {
        dbc = new DataBaseConnection();
    }
    
    public Factura getFactura(int codigo) throws Exception{
        String query = "SELECT * " + "FROM Cliente WHERE Codigo = '%d'";
        query = String.format(query, codigo);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return factura(rs);
        }
        else {
            throw new Exception("Factura inexistente.");
        }
    }
    public LinkedList<Factura> searchFacturaByCliente(String ced) throws Exception{
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Cliente like '%%%s%%'";
            query = String.format(query, ced);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public LinkedList<Factura> searchFacturaByVendedor(String idEmp) throws Exception{
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Vendedor like '%%%s%%'";
            query = String.format(query, idEmp);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public LinkedList<Factura> searchInventarioByDate(Date fecha) throws Exception{
        
        fecha = getTimeFormat(fecha);
        
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Fecha like '%%%s%%'";
            query = String.format(query, fecha);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deleteFactura(String cod) throws Exception{
        String query = "DELETE " + "FROM Factura WHERE Codigo = '%s'";
        query = String.format(query, cod);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Factura inexistente.");
        }
    }
    public void addFactura(Factura fac) throws Exception{
        
        Date fecha = getTimeFormat(fac.getFecha());
        
        String query = "INSERT INTO Factura (Cliente, Vendedor, Fecha, isPagado, isDespachado)"
                    + "VALUES('%s', '%s', '%s', '%b', '%b')";
        query = String.format(query, fecha, fac.getCliente().getCedula(), fac.getVendedor().getIdEmpleado(), fac.isPagado(), fac.isDespachado());
        int result = dbc.executeUpdate(query);
        if(result != 0)
            addDetalles(fac);
        else 
            throw new Exception("Factura existente.");
    }
    
    public void addDetalles(Factura fac) {
        LinkedList<LineaDetalle> detalles = fac.getDetalles();
        String query;
        for(LineaDetalle detalle : detalles) {
            query = "INSERT INTO LineaDetalle (Factura, Producto, Cantidad, Precio, isDespachado)" + 
                       "VALUES ('%d', '%s', '%d', '%d', '%b')";
            query = String.format(query, fac.getCodigo(), detalle.getProducto(), detalle.getCantidad(), detalle.getPrecio(), detalle.isDespachado());
            int result = dbc.executeUpdate(query);
        }        
    }
    
    public void updateFactura(Factura fac) throws Exception{
        
        Date fecha = getTimeFormat(fac.getFecha());
        
        String query = "UPDATE Inventario SET Cliente = '%s', Vendedor = '%d', Fecha = '%s', isPagado = '%b', isDespachado = '%b'" + 
                        "WHERE Codigo = '%d'";
        query = String.format(query, fac.getCliente(), fac.getVendedor(), fecha, fac.isPagado(), fac.isDespachado(), fac.getCodigo());
        int result = dbc.executeUpdate(query);
        if(result != 0)
            updateDetalles(fac);
        else 
            throw new Exception("Inventario inexistente.");            
    }
    
    public void updateDetalles(Factura fac) {
        LinkedList<LineaDetalle> detalles = fac.getDetalles();
        String query;
        for(LineaDetalle detalle : detalles) {
            query = "UPDATE LineaDetalle SET Cantidad = '%d', Precio = '%d', isDespachado = '%b'" +
                    "WHERE Factura = '%d' AND Producto = '%s'";
            query = String.format(query, detalle.getCantidad(), detalle.getPrecio(), detalle.isDespachado(), fac.getCodigo(), detalle.getProducto());
            int result = dbc.executeUpdate(query);
        }
    }
    
    private Factura factura(ResultSet rs) throws Exception {
        try {
            Factura fac = new Factura();
            fac.setCodigo(rs.getInt("Codigo"));

            String cedCliente = rs.getString("Cliente");
            fac.setCliente(getCliente(cedCliente));

            String cedVendedor = rs.getString("Vendedor");            
            fac.setVendedor(getVendedor(cedVendedor));

            fac.setFecha(rs.getDate("Fecha"));
            fac.setPagado(rs.getBoolean("isPagado"));
            fac.setDespachado(rs.getBoolean("isDespachado"));
            
            LinkedList<LineaDetalle> detalles = searchDetallesByFactura(fac.getCodigo());
            fac.setDetalles(detalles);
            
            return fac;            
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Cliente getCliente(String ced) throws Exception {
        ConjuntoClientes cp = new ConjuntoClientes(dbc);
        return cp.getClienteByID(ced);
    }
    public Empleado getVendedor(String id) throws Exception {
        ConjuntoEmpleados cp = new ConjuntoEmpleados(dbc);
        return cp.getEmpleadoByID(id);
    }
    public Producto getProducto(String cod) throws Exception {
        ConjuntoProductos cp = new ConjuntoProductos(dbc);
        return cp.getProductoByCod(cod);
    }
    public LinkedList<LineaDetalle> searchDetallesByFactura(int cod) throws Exception{
        LinkedList<LineaDetalle> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM LineaDetalle WHERE Factura like '%d%'";
            query = String.format(query, cod);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(detalle(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    private LineaDetalle detalle (ResultSet rs) throws Exception {
            LineaDetalle ld = new LineaDetalle();
            String codProducto = rs.getString("Producto");
            ld.setProducto(getProducto(codProducto));
            ld.setCantidad(rs.getInt("Cantidad"));
            ld.setPrecio(rs.getDouble("Precio"));
            ld.setDespachado(rs.getBoolean("isDespachado"));
            return ld;        
    }
    
    private Date getTimeFormat(Date fecha) {
        try {
        SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        return dt.parse(fecha.toString());        
        } catch(Exception e) {
            return new Date(1999, 12, 29, 00, 00, 00);
        }
    }
    
    DataBaseConnection dbc;
}
