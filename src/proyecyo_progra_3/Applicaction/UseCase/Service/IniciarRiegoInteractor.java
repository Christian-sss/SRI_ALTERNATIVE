/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.UseCase.Service;

import proyecyo_progra_3.Applicaction.UseCase.Service.UseCase.IniciarRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.RiegoPort;
import proyecyo_progra_3.Domain.Ports.Input.SensorPort;
import proyecyo_progra_3.Domain.Service.SeguridadHidrica;

/**
 *
 * @author Usuario
 */
public class IniciarRiegoInteractor implements IniciarRiegoUseCase {
    
    private final RiegoPort port;
    private final SeguridadHidrica seguridadHidrica;
    private final SensorPort sensor;
    
    
    
    public IniciarRiegoInteractor(RiegoPort port, SeguridadHidrica sh,SensorPort sensor) {
        this.port = port;
        this.seguridadHidrica = sh;
        this.sensor = sensor;
        
    }

    @Override
    public void ejecutar() {
        
        var nivel = sensor.obtenerNivelTanque();
        
        
        if(!seguridadHidrica.puedeRegar(0)) {
               System.out.println("ERROR");
            
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
