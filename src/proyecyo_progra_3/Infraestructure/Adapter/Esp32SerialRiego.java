/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Adapter;

/**
 *
 * @author Usuario
 */


import com.fazecast.jSerialComm.SerialPort;
import proyecyo_progra_3.Domain.Ports.Input.RiegoPort;

public class Esp32SerialRiego implements RiegoPort {
    
    
    private final SerialPort port;
    
    
   
    public Esp32SerialRiego(SerialPort port) {
        this.port = port;
    }
    
    
    @Override
    public void iniciarRiego() {
        enviar("RIEGO_ON\n");
    }

    @Override
    public void detenerRiego() {
        enviar("RIEGO_OFF\n");
    }
    
    
    
    private void enviar(String comando) {
        byte[] data = comando.getBytes();
        port.writeBytes(data, data.length);
    }


    
    
    
}
