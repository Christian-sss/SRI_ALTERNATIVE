package proyecyo_progra_3.Domain.Ports.Output;

import proyecyo_progra_3.Domain.Model.TanqueAgua;

public interface SeguridadHidrica {

    void evaluarEstado(TanqueAgua tanque);
    boolean puedeRegar(TanqueAgua tanque);
}
