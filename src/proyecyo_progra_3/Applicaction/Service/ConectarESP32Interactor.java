package proyecyo_progra_3.Applicaction.Service;

import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialAdapter;
import proyecyo_progra_3.Infraestructure.Config.Esp32ConnectionManager;




public class ConectarESP32Interactor {

    private final Esp32ConnectionManager connectionManager;
    private final Esp32SerialAdapter serialAdapter;

    public ConectarESP32Interactor(Esp32ConnectionManager connectionManager,
                                   Esp32SerialAdapter serialAdapter) {
        this.connectionManager = connectionManager;
        this.serialAdapter = serialAdapter;
    }

    public boolean ejecutar(String puertoString) {
        boolean conectado = connectionManager.conectar(puertoString);


        if (conectado)
            serialAdapter.usarPuerto(connectionManager.getPuerto());


        return conectado;
    }

}
