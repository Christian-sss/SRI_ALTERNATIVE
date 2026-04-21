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
import proyecyo_progra_3.Domain.Ports.Output.RiegoPort;
import proyecyo_progra_3.Infraestructure.Config.Esp32ConnectionManager;


public class Esp32SerialRiego implements RiegoPort {
    

     private final int MODO_AUTOMATICO = 2;
    private final Esp32ConnectionManager port;
    
    public Esp32SerialRiego(Esp32ConnectionManager connectionManager) {
        this.port = connectionManager;
    }
    

    @Override
    public void enviarComando(int comando) {
        if (port != null && port.estaConectado()) {
            String cmd = comando + "\n";
            byte[] data = cmd.getBytes();
            port.escribirABytes(data);
        } else {
            System.err.println("Puerto no se logro conectar");
        }
    }


    public void modoAutomatico() {
        enviarComando(MODO_AUTOMATICO);
        System.out.println("[JAVA] 🔄 Comando MODO AUTOMÁTICO (2) enviado");
    }
    
}
