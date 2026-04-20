/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.UseCase.Service;

import proyecyo_progra_3.Domain.Ports.Input.RiegoPort;

/**
 *
 * @author Usuario
 */
public class DetenerRiegoInteractor {
    
    
    private final RiegoPort port;

    
    
    public DetenerRiegoInteractor(RiegoPort port) {
        this.port = port;
    }
    
    
    
    public void ejecutar() {
        port.detenerRiego();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
