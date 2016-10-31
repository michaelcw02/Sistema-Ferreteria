/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.clientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoClientes {
    
    public ConjuntoClientes(DataBaseConnection dbc) {
        this.dbc = dbc;
    }
    
    public Cliente getClienteByID(String id) throws Exception{
        String query = "SELECT * " + "FROM Cliente WHERE Cedula = '%s'";
        query = String.format(query, id);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return cliente(rs);
        }
        else {
            throw new Exception("Cliente inexistente.");
        }
    }
    public LinkedList<Cliente> searchClienteByName(String nombre) throws Exception{
        LinkedList<Cliente> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Cliente WHERE Nombre like '%%%s%%'";
            query = String.format(query, nombre);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(cliente(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deleteCliente(String id) throws Exception{
        String query = "DELETE FROM Cliente WHERE Cedula = '%s'";
        query = String.format(query, id);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Cliente inexistente.");
        }
    }
    public void addCliente(Cliente client) throws Exception{
        String query = "INSERT INTO EMPLEADO (Cedula, Nombre, Telefono, Email, Descuento) "
                    + "VALUES('%s', '%s', '%s', '%s', %d)";
        query = String.format(query, client.getCedula(), client.getNombre(), 
                client.getTelefono(), client.getEmail(), client.getDescuento());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Cliente existente.");
        }
    }
    public void updateCliente(Cliente client) throws Exception{
        String query = "UPDATE Empleado SET Nombre = '%s', Telefono = '%s', Email = '%b', Descuento = '%d' " + 
                        "WHERE Cedula = '%s'";
        query = String.format(query, client.getNombre(), client.getTelefono(), client.getEmail(), 
                            client.getDescuento(), client.getCedula());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Cliente inexistente.");
        }
    }
    
    private Cliente cliente(ResultSet rs) {
        try {
            Cliente client = new Cliente();
            client.setCedula(rs.getString("Cedula"));
            client.setNombre(rs.getString("Nombre"));
            client.setTelefono(rs.getString("Telefono"));
            client.setEmail(rs.getString("Email"));
            client.setDescuento(rs.getInt("Descuento"));
            return client;            
        } catch (SQLException ex) {
            return null;
        }
    }
    
    DataBaseConnection dbc;
}
