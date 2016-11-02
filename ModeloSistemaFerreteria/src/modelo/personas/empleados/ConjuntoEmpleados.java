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
    public Empleado getEmpleadoByIdAndPass(String id, String pass) throws Exception{
        String query = "SELECT * " + "FROM Empleado WHERE ID_EMPLEADO = '%s' AND Clave = '%s'";
        query = String.format(query, id, pass);
        ResultSet rs = dbc.executeQuery(query);
        if(rs.next()) {
            return empleado(rs);
        }
        else {
            throw new Exception("Cliente inexistente.");
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
        String query = "DELETE FROM Empleado WHERE ID_EMPLEADO = '%s'";
        query = String.format(query, id);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Empleado inexistente.");
        }
    }
    public void addEmpleado(Empleado emp) throws Exception{
        String query = "INSERT INTO `ferreteriadatos`.`empleado` (`ID_EMPLEADO`, `Nombre`, `Clave`, `isActivo`, `isAdministrador`, `isCajero`, `isVendedor`, `isDespachador`, `isBodeguero`) VALUES ('%s', '%s', '%s', '%d', '%d', '%d', '%d', '%d', '%d');";
        query = String.format(query, emp.getIdEmpleado(), emp.getNombre(), emp.getClave(), toInt(emp.isActivo()), toInt(emp.isAdministrador()), toInt(emp.isBodeguero()), toInt(emp.isCajero()), toInt(emp.isDespachador()), toInt(emp.isVendedor()));
        System.out.println(query);
        int result = dbc.executeUpdate(query);
        if(result == 0) {
            throw new Exception("Empleado existente.");
        }
    }
    public void updateEmpleado(Empleado emp) throws Exception{
        String query = "UPDATE `ferreteriadatos`.`empleado` SET `Nombre`='%s', `Clave`='%s', `isActivo`='%d', `isAdministrador`='%d', `isCajero`='%d', `isVendedor`='%d', `isDespachador`='%d', `isBodeguero`='%d' WHERE `ID_EMPLEADO`='%s';";
        query = String.format(query, emp.getNombre(), emp.getClave(), toInt(emp.isActivo()), toInt(emp.isAdministrador()), toInt(emp.isBodeguero()), toInt(emp.isCajero()), toInt(emp.isDespachador()), toInt(emp.isVendedor()), emp.getIdEmpleado());
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
    int toInt(boolean b) {
        return Boolean.compare(b, false);
    }
    
    
    DataBaseConnection dbc;
}
