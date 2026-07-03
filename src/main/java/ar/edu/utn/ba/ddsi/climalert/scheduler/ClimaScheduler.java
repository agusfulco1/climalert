package ar.edu.utn.ba.ddsi.climalert.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {
    private final ClimaService climaService;


    public ClimaScheduler(ClimaService climaService) {
        this.climaService = climaService;
    }

    @Scheduled(fixedRate = 300000) //Que se ejecute cada 5 minutos
    public void obtenerClima() {
        climaService.getClima();
    }
}
