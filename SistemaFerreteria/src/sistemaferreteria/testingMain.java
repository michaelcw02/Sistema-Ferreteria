/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaferreteria;

import modelo.database.DataBaseConnection;
import modelo.personas.empleados.*;

/**
 *
 * @author Michael Chen W.
 */
public class testingMain {
    
    public static void main(String[] args) {
        
        try {
            DataBaseConnection dbc = new DataBaseConnection();
            ConjuntoEmpleados cEmp = new ConjuntoEmpleados(dbc);
            Empleado emp = null;
            
            emp = cEmp.getEmpleadoByID("12345");
            System.out.println(emp.getNombre());
            
            emp = new Empleado ("111", "LOLA", "LOLA", true, true, true, true, true, true);
            
            cEmp.addEmpleado(emp);
            emp = null;
            emp = cEmp.getEmpleadoByID("111");
            System.out.println(emp.getNombre());
            
        } catch (Exception e) {
            
        }
    }
    
}
