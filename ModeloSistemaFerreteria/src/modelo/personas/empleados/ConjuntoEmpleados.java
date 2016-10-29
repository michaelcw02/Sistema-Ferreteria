/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.empleados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import modelo.database.DataBaseConnection;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoEmpleados {

    public ConjuntoEmpleados() {
        dbc = new DataBaseConnection();
    }

    public ConjuntoEmpleados(DataBaseConnection dbc) {
        this.dbc = dbc;
    }
    
    
    public Empleado getEmpleadoByID(String id) throws Exception{
        String query = "SELECT * " + "FROM Empleado WHERE ID_EMPLEADO = '%s'";
        query = String.format(query, id);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return empleado(rs);
        }
        else {
            throw new Exception("Empleado inexistente.");
        }
    }
    public LinkedList<Empleado> searchEmpleadoByName(String nombre) throws Exception{
        LinkedList<Empleado> listaResultado = new LinkedList<>();
        try {
            String query = "SELECT * " + "FROM Empleado WHERE Nombre like '%%%s%%'";
            query = String.format(query, nombre);
            ResultSet rs = dbc.executeQuery(query);
            while (rs.next()) {
                listaResultado.add(empleado(rs));
            }
        } catch (SQLException ex) {
        }
        return listaResultado;
    }
    public void deleteEmpleado(String id) throws Exception{
        String query = "DELETE " + "FROM Empleado WHERE ID_EMPLEADO = '%s'";
        query = String.format(query, id);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Empleado inexistente.");
        }
    }
    public void addEmpleado(Empleado emp) throws Exception{
        String query = "INSERT INTO Empleado (ID_EMPLEADO, Nombre, Clave, isActivo, isAdministrador, isBodeguero, isCajero, isDespachador, isVendedor) "
                    + "VALUES('%s', '%s','%s','%b','%b','%b','%b','%b')";
        query = String.format(query, emp.getIdEmpleado(), emp.getNombre(), emp.getClave(), emp.isActivo(), emp.isAdministrador(), 
                                    emp.isBodeguero(), emp.isCajero(), emp.isDespachador(), emp.isVendedor());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Empleado existente.");
        }
    }
    public void updateEmpleado(Empleado emp) throws Exception{
        String query = "UPDATE Empleado SET Nombre = '%s', Clave = '%s', isActivo = '%b', isAdministrador = '%b', " + 
                        "isBodeguero = '%b', isCajero = '%b', isDespachador = '%b', isVendedor = '%b' " + 
                        "WHERE ID_EMPLEADO = '%s'";
        query = String.format(query, emp.getNombre(), emp.getClave(), emp.isActivo(), emp.isAdministrador(), emp.isBodeguero(), 
                                    emp.isCajero(), emp.isDespachador(), emp.isVendedor(), emp.getIdEmpleado());
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Empleado inexistente.");
        }
    }
    
    private Empleado empleado(ResultSet rs) {
        try {
            Empleado emp = new Empleado();
            emp.setIdEmpleado(rs.getString("ID_Empleado"));
            emp.setNombre(rs.getString("Nombre"));
            emp.setClave(rs.getString("Clave"));
            emp.setActivo(rs.getBoolean("isActivo"));
            emp.setVendedor(rs.getBoolean("isVendedor"));
            emp.setCajero(rs.getBoolean("isCajero"));
            emp.setDespachador(rs.getBoolean("isDespachador"));
            emp.setBodeguero(rs.getBoolean("isBodeguero"));
            emp.setAdministrador(rs.getBoolean("isAdministrador"));
            return emp;            
        } catch (SQLException ex) {
            return null;
        }
    }
        
    DataBaseConnection dbc;
}
