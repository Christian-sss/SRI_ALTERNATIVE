package proyecyo_progra_3.Applicaction.Service;

import proyecyo_progra_3.Domain.ENUMS.EstadoSistema;
import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Input.DetenerRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.IniciarRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.RiegoAutomatizadoUseCase;
import proyecyo_progra_3.Domain.Ports.Output.EstadisticasRepository;


public class RiegoAutomatizadoInteractor implements RiegoAutomatizadoUseCase {


    private final IniciarRiegoUseCase riegoInicioManual;
    private final DetenerRiegoUseCase riegoDetenido;
    private final TanqueAgua tanque;
    private EstadisticasRepository estadisticasRepository;

    private static final int HUMEDAD_LIMITE = 60;

    public RiegoAutomatizadoInteractor(IniciarRiegoUseCase riegoInicio, DetenerRiegoUseCase riegoDetenido, TanqueAgua tanque, EstadisticasRepository repository) {
        this.riegoInicioManual = riegoInicio;
        this.riegoDetenido = riegoDetenido;
        this.tanque = tanque;
        this.estadisticasRepository = repository;
    }

    @Override
    public String ejecutar() {

        riegoInicioManual.ejecutar();


        if (tanque.getEstadoActual() == EstadoSistema.BLOQUEADO_SIN_AGUA) {

            return " Automático cancelado: " + tanque.getEstadoActual().descripcion();
        }

        iniciarVigilanteEnSegundoPlano();

        return "Riego Inteligente INICIADO. Se detendrá solo al alcanzar " + HUMEDAD_LIMITE + "% de humedad.";

    }

    private void iniciarVigilanteEnSegundoPlano() {
        new Thread(() -> {
            try {
                System.out.println(" Iniciando monitoreo inteligente...");
                int humedadAlIniciar = tanque.getHumedad();

                while (tanque.isBombaActiva() && tanque.getHumedad() < HUMEDAD_LIMITE && tanque.hayAgua()) {
                    System.out.println(" Humedad: " + tanque.getHumedad() +
                            "%, Distancia Agua: " + tanque.getDistancia() + "cm");
                    Thread.sleep(2000);
                }


                System.out.println("REPORTE");
                if (!tanque.isBombaActiva()) System.out.println("Alguien apagó la bomba manualmente.");
                if (tanque.getHumedad() >= HUMEDAD_LIMITE) System.out.println("Motivo: Humedad objetivo (" + HUMEDAD_LIMITE + "%) alcanzada.");
                if (!tanque.hayAgua()) System.out.println("EL TANQUE SE QUEDÓ SIN AGUA! (Distancia > 18cm)");

                estadisticasRepository.registrarSesionRiego(
                        "AUTOMATICO",
                        humedadAlIniciar,
                        tanque.getHumedad(),
                        ""
                );



                if (tanque.isBombaActiva()) {
                    System.out.println(" Condición de parada alcanzada. Deteniendo bomba...");
                    riegoDetenido.ejecutar();
                }

            } catch (InterruptedException e) {
                System.err.println("[AUTO] Hilo de monitoreo interrumpido.");
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.err.println(" ERROR FATAL EN EL HILO SECUNDARIO:");
                e.printStackTrace();
            }
        }).start();
    }


}
