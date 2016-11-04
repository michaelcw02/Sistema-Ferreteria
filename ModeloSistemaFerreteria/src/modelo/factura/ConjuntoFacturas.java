/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.factura;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;
import modelo.inventarios.Inventario;
import modelo.personas.clientes.*;
import modelo.personas.empleados.*;
import modelo.productos.*;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoFacturas {

    public ConjuntoFacturas(DataBaseConnection dbc) {
        this.dbc = dbc;
    }
    
    public LinkedList<Factura> getAllFacturas() {
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura;";
            query = String.format(query);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next())
                listaResultado.add(factura(rs));
        } catch (Exception e) {
            
        }
        return listaResultado;
    }
    public Factura getFacturaByCod(int codigo) throws Exception{
        String query = "SELECT * " + "FROM Factura WHERE Codigo = '%d'";
        query = String.format(query, codigo);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return factura(rs);
        }
        else {
            throw new Exception("Factura inexistente.");
        }
    }
    public LinkedList<Factura> searchFacturaByCliente(String ced) {
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Cliente like '%%%s%%'";
            query = String.format(query, ced);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (Exception ex) {
        }
        return listaResultado;
    }
    public LinkedList<Factura> searchFacturaByVendedor(String idEmp) {
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Vendedor like '%%%s%%'";
            query = String.format(query, idEmp);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (Exception ex) {
        }
        return listaResultado;
    }
    public LinkedList<Factura> searchFacturaByDate(Date fecha) {
        
        LinkedList<Factura> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Factura WHERE Fecha like '%%%s%%'";
            query = String.format(query, dateToSQL(fecha));
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(factura(rs));
            }
        } catch (Exception ex) {
        }
        return listaResultado;
    }
    public void deleteFactura(String cod) throws Exception{
        String query = "DELETE FROM Factura, LineaDetalle WHERE Codigo = '%s'";
        query = String.format(query, cod);  //TALVEZ DA ERROR EL QUERY
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Factura inexistente.");
        }
    }
    public void addFactura(Factura fac) throws Exception{
        
        String query = "INSERT INTO `ferreteriadatos`.`factura` (`Cliente`, `Vendedor`, `Fecha`, `isPagado`, `isDespachado`) VALUES ('%s', '%s', '%s', '%d', '%d');";
        query = String.format(query, fac.getCliente().getCedula(), fac.getVendedor().getIdEmpleado(), dateToSQL(fac.getFecha()), toInt(fac.isPagado()), toInt(fac.isDespachado()));
        int result = dbc.executeUpdate(query);
        if(result != 0)
            addDetalles(fac);
        else 
            throw new Exception("Factura existente.");
    }
    
    public void addDetalles(Factura fac)  throws Exception{
        LinkedList<LineaDetalle> detalles = fac.getDetalles();
        String query;
        for(LineaDetalle detalle : detalles) {
            query = "INSERT INTO `ferreteriadatos`.`lineadetalle` (`Factura`, `Producto`, `Cantidad`, `Precio`, `isDespachado`) VALUES ('%d', '%s', '%d', '%f', '%d');";
            query = String.format(query, fac.getCodigo(), detalle.getProducto().getCodigo(), detalle.getCantidad(), detalle.getPrecio(), toInt(detalle.isDespachado()));
            int result = dbc.executeUpdate(query);
            if (result == 0)
                throw new Exception("No se pudo agregar el detalle..");
        }        
    }
    
    public void updateFactura(Factura fac) throws Exception{
                       
        String query = "UPDATE `ferreteriadatos`.`factura` SET `Cliente`='%s', `Vendedor`='%s', `Fecha`='%s', `isPagado`='%d', `isDespachado`='%d' WHERE `Codigo`='%d';";
        query = String.format(query, fac.getCliente().getCedula(), fac.getVendedor().getIdEmpleado(), dateToSQL(fac.getFecha()), toInt(fac.isPagado()), toInt(fac.isDespachado()), fac.getCodigo());
        int result = dbc.executeUpdate(query);
        if(result != 0)
            updateDetalles(fac);
        else 
            throw new Exception("Factura inexistente.");            
    }
    
    public void updateDetalles(Factura fac) {
        LinkedList<LineaDetalle> detalles = fac.getDetalles();
        String query;
        for(LineaDetalle detalle : detalles) {
            query = "UPDATE `ferreteriadatos`.`lineadetalle` SET `Cantidad`='%d', `Precio`='%f', `isDespachado`='%d' WHERE `Factura`='%d' and`Producto`='%s';";
            query = String.format(query, detalle.getCantidad(), detalle.getPrecio(), toInt(detalle.isDespachado()), fac.getCodigo(), detalle.getProducto().getCodigo());
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
    
    private String dateToSQL(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    private int toInt(boolean b) {
        return Boolean.compare(b, false);
    }
    
    DataBaseConnection dbc;
}
