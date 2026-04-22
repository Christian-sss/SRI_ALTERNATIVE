/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;


import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Input.DetenerRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Output.RiegoPort;
import proyecyo_progra_3.Domain.Service.SistemaRiegoService;

/**
 *
 * @author Usuario
 */
public class DetenerRiegoInteractor implements DetenerRiegoUseCase {
    
    
    private final RiegoPort port;
    private final TanqueAgua tanque;
    private final SistemaRiegoService service;

    public DetenerRiegoInteractor(RiegoPort port, TanqueAgua tanque, SistemaRiegoService service) {
        this.port = port;
        this.tanque = tanque;
        this.service =service;
    }
    


    @Override
    public String ejecutar() {


            if (!tanque.isBombaActiva()) {
                return "La bomba ya está detenida";
            }
        

        port.enviarComando(0);

        service.detenerRiego(tanque);



        return "Riego detenido correctamente";
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}
