package proyecyo_progra_3.Infraestructure.Config;




import com.fazecast.jSerialComm.SerialPort;

public class Esp32ConnectionManager {

    private SerialPort puerto;

    public boolean conectar(String puertoNombre) {
        puerto = SerialPort.getCommPort(puertoNombre);
        puerto.setBaudRate(115200);
        return puerto.openPort();
    }

    public boolean estaConectado() {
        return puerto != null && puerto.isOpen();
    }
    



    // reemplaza la participacion del writeBytes en el Adapter con esta funciion void
    public void escribirABytes(byte[] data) {
        puerto.writeBytes(data, data.length);
    }

    public SerialPort getPuerto() {
        return puerto;
    }
}
