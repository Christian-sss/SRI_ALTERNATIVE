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
import proyecyo_progra_3.Domain.Ports.Input.RiegoPortUseCase;


public class Esp32SerialRiego implements RiegoPortUseCase {
    
    
    private final int RIEGO_ON = 1;
    private final int RIEGO_OFF = 0;
    private final int MODO_AUTOMATICO = 2;
    
    private final SerialPort port;
    
    
   
    public Esp32SerialRiego(SerialPort port) {
        this.port = port;
    }
    
    
    @Override
    public void iniciarRiego() {
        enviar(RIEGO_ON);
        System.out.println("[JAVA] Comando ENCENDER (1) enviado");
    }

    @Override
    public void detenerRiego() {
        enviar(RIEGO_OFF);
        System.out.println("[JAVA] ?Comando APAGAR (0) enviado");
    }
    
    
    public void modoAutomatico() {
        enviar(MODO_AUTOMATICO);
        System.out.println("[JAVA] 🔄 Comando MODO AUTOMÁTICO (2) enviado");
    }
    
    
/*    
    private void enviar(int comando) {
        String cmd = comando + "\n";
        byte[] data = cmd.getBytes();
        port.writeBytes(data, data.length);
    }

*/
    private void enviar(int comando) {
        if (port != null && port.isOpen()) {
            String cmd = comando + "\n";
            byte[] data = cmd.getBytes();
            port.writeBytes(data, data.length);
        } else {
            System.err.println("[JAVA] Error: Puerto no conectado");
        }
    }
    
    
    
}
