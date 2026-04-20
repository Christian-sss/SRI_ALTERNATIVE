/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Model;

/**
 *
 * @author Usuario
 */
public class SensorHumedad {
    
    
    
    private int porcentajeHumedad;
    private int valorRaw;
   
    
    public SensorHumedad(int porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }
    
    public int getPorcentajeHumedad() {
        return porcentajeHumedad;
    }
    
    public boolean estaSeco() {
        return porcentajeHumedad < 30;
    }
    
    public boolean estaHumedo() {
        return porcentajeHumedad > 70;
    }
}
