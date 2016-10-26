/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadores;

import interfaces.Observer;
import interfaces.Subject;
import java.util.LinkedList;

/**
 *
 * @author Estudiante
 */
public class AdaptadorSubject implements Subject{
    
    public AdaptadorSubject() {
        vistas = new LinkedList<>();
    }

    @Override
    public void agregar(Observer e) {
        vistas.add(e);
    }
    @Override
    public void remover(Observer e) {
        vistas.remove(e);
    }
    @Override
    public void notificar() {
        for (Observer e: vistas)
            e.update();
    }

    private final LinkedList<Observer> vistas;
}
