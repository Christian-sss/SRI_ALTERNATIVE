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
public class SistemaRiegoService {


    private final SeguridadHidricaService seguridad;

    public SistemaRiegoService(SeguridadHidricaService seguridad) {
        this.seguridad = seguridad;
    }


    public void activarRiego(TanqueAgua tanque) {
        if(seguridad.puedeRegar(tanque))
            tanque.activarBomba();
    }

    public void detenerRiego(TanqueAgua tanque) {
        tanque.desactivarBomba();
    }

}
