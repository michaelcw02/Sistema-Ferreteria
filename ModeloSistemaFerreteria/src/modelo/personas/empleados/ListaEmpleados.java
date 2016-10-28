/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.empleados;

import modelo.personas.empleados.Empleado;
import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ListaEmpleados {

    public ListaEmpleados() {
        empleados = new LinkedList<>();
    }
    
    public void agregar(Empleado emp) {
        empleados.add(emp);
    }
    public boolean remover(int id) {
        for(Empleado emp : empleados)
            if(emp.getIdEmpleado() == id) {
                emp.setActivo(false);
                return true;
            }
        return false;
    }
    
    LinkedList<Empleado> empleados;
}
