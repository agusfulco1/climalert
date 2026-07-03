package ar.edu.utn.ba.ddsi.climalert.scheduler;

import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {
    private final ClimaService climaService;


    public ClimaScheduler(ClimaService climaService) {
        this.climaService = climaService;
    }

    @Scheduled(fixedRate = 120000) //Que se ejecute cada 5 minutos
    public void obtenerClima() {
        climaService.getClima();
    }

    @Scheduled(fixedRate = 60000)
    public void evaluarAlerta() {
        climaService.evaluarClima();
    }
}
