/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecyo_progra_3.Domain.Ports.Output;

import java.util.function.Consumer;
import proyecyo_progra_3.Domain.Model.EstadoSistema;

/**
 *
 * @author Usuario
 */
public interface SerialPortOutput {
    
    
    
    void enviarComando(String comando);
    void agregarListener(Consumer<EstadoSistema> listener);
    EstadoSistema getEstadoActual();
}
