/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Adapter;

import proyecyo_progra_3.Domain.Ports.Input.SensorPort;
import proyecyo_progra_3.Domain.Ports.Input.RiegoPort;

/**
 *
 * @author Usuario
 */


import com.fazecast.jSerialComm.SerialPort;



public class Esp32SerialAdapter implements SensorPort {

    
   private SerialPort serial;
   
   
   public Esp32SerialAdapter(SerialPort serial) {
       this.serial = serial;
   }
    
    @Override
    public double obtenerHumedad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
    
    @Override
    public double obtenerNivelTanque() {
        return 0.0;
    }
    
}
