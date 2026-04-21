/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Service;

import proyecyo_progra_3.Domain.Model.TanqueAgua;

/**
 *
 * @author Usuario
 */
public class SeguridadHidrica {


    public boolean puedeRegar(TanqueAgua tanque) {
        return tanque.hayAgua() && tanque.getHumedad() < 30;
    }
    
    
    
}
