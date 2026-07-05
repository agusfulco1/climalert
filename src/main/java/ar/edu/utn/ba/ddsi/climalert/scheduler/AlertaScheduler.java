package ar.edu.utn.ba.ddsi.climalert.scheduler;

import ar.edu.utn.ba.ddsi.climalert.services.AlertaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertaScheduler {
    private final AlertaService alertaService;

    public AlertaScheduler(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @Scheduled(fixedRate = 60000) //Que se ejecute cada minuto
    public void evaluarAlerta() {
        alertaService.procesarNuevasAlertas();
    }
}
