/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaferreteria;

import control.Control;
import modelo.database.DataBaseConnection;

/**
 *
 * @author Michael Chen W.
 */
public class SistemaFerreteria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DataBaseConnection conn = new DataBaseConnection();
        
        Control c = new Control();
        c.mostrarLogin();
    }
    
}
