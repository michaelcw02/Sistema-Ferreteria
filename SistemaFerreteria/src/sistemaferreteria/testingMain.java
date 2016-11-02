/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaferreteria;

import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.database.DataBaseConnection;
import modelo.factura.*;
import modelo.inventarios.*;
import modelo.personas.clientes.*;
import modelo.personas.empleados.*;
import modelo.productos.*;

/**
 *
 * @author Michael Chen W.
 */
public class testingMain {
    
    public static void main(String[] args) {
        
        try {
            DataBaseConnection dbc = new DataBaseConnection();
            
            ConjuntoInventarios ci = new ConjuntoInventarios(dbc);
            ConjuntoProductos   cp = new ConjuntoProductos(dbc);
            ConjuntoFacturas    cf = new ConjuntoFacturas(dbc);
            
            String query = "INSERT INTO `ferreteriadatos`.`empleado` (`ID_EMPLEADO`, `Nombre`, `Clave`, `isActivo`, `isAdministrador`, `isCajero`, `isVendedor`, `isDespachador`, `isBodeguero`) VALUES ('%s', '%s', '%s', '%b', '%b', '%b', '%b', '%b', '%b');";
            query = "INSERT INTO `ferreteriadatos`.`empleado` (`ID_EMPLEADO`, `Nombre`, `Clave`, `isActivo`, `isAdministrador`, `isCajero`, `isVendedor`, `isDespachador`, `isBodeguero`) VALUES ('12', '23', '23', '1', '1', '1', '1', '1', '1');";
        
            java.util.Date dt = new java.util.Date();            
            
            Cliente c = new Cliente("0201", "Beto", "87654321", "beto.com", 10);
            Empleado e = new Empleado("12345", "Michael Chen", "12345", true, true, true, true, true, true);
            
            Factura f = new Factura(c, e, dt);
            
            cf.addFactura(f);
            
            //dbc.executeUpdate(query);
            /*
            Producto emp = null;
            
            //System.out.println(emp.getNombre());
            
            emp = new Producto("1", "1", "1", 12.0, true);
            
            cc.addProducto(emp);
            
            System.out.println(emp.getDescripcion());
            
            emp = new Producto("1", "LOLA", "LOLA", 14.0, false);
            
            cc.updateProducto(emp);
            
            System.out.println(emp.getDescripcion());
            
            cc.deleteProducto("1");
            */
        } catch (Exception e) {
            
        }
    }
    private Date getTimeFormat(Date fecha) {
        try {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
        return dt.parse(fecha.toString());        
        } catch(Exception e) {
            return new Date(1999, 12, 29);
        }
    }
    
}
