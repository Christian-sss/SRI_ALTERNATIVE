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


    private final SeguridadHidrica seguridad;

    public SistemaRiegoService(SeguridadHidrica seguridad) {
        this.seguridad = seguridad;
    }

    public boolean puedeIniciarRiego(TanqueAgua tanque) {
        return seguridad.puedeRegar(tanque);
    }

    public void activarRiego(TanqueAgua tanque) {
        tanque.activarBomba();
    }

    public void detenerRiego(TanqueAgua tanque) {
        tanque.desactivarBomba();
    }
    
}
