package ar.edu.utn.ba.ddsi.climalert.scheduler;

import ar.edu.utn.ba.ddsi.climalert.services.ClimaServices;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {
    private final ClimaServices climaServices;


    public ClimaScheduler(ClimaServices climaServices) {
        this.climaServices = climaServices;
    }

    @Scheduled(fixedRate = 300000) //Que se ejecute cada 5 minutos
    public void obtenerClima() {
        climaServices.getClima();
    }
}
