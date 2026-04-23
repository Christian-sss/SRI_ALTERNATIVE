/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Service;

import proyecyo_progra_3.Domain.ENUMS.EstadoSistema;
import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Output.SeguridadHidrica;

/**
 *
 * @author Usuario
 */
public class SeguridadHidricaService implements SeguridadHidrica {



    @Override
    public boolean puedeRegar(TanqueAgua tanque) {
        return tanque.hayAgua() && tanque.getHumedad() < 30;
    }


    @Override
    public void evaluarEstado(TanqueAgua tanque) {

        if (!tanque.hayAgua()) {
            tanque.setEstadoActual(EstadoSistema.BLOQUEADO_SIN_AGUA);

            tanque.desactivarBomba();
            return;
        }

        if (tanque.isBombaActiva()) {
            tanque.setEstadoActual(EstadoSistema.REGANDO);
            return;
        }


        tanque.setEstadoActual(EstadoSistema.ESPERA);
    }



}
