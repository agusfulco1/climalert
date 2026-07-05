package ar.edu.utn.ba.ddsi.climalert.services.Impl;

import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.entities.EvaluadorAlertas;
import ar.edu.utn.ba.ddsi.climalert.exceptions.NullException;
import ar.edu.utn.ba.ddsi.climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import ar.edu.utn.ba.ddsi.climalert.services.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaServiceImpl implements AlertaService {

    private final EmailService emailService;
    private final ClimaService climaService;
    private final List<EvaluadorAlertas> alertas; //Crea una lista con todas las clases hijas con el @component

    private Long idUltimoClimaProcesado = null;

    public AlertaServiceImpl(EmailService emailService, ClimaService climaService, List<EvaluadorAlertas> alertas) {
        this.emailService = emailService;
        this.climaService = climaService;
        this.alertas = alertas;
    }
    @Override
    public void procesarNuevasAlertas(){
        Clima ultimoClima = climaService.obtenerUltimoClima();
        if (ultimoClima == null) {
            return;
        }

        if ( idUltimoClimaProcesado != null && idUltimoClimaProcesado.equals(ultimoClima.getId())){
            return;
        }
        for (EvaluadorAlertas alerta : alertas) {
            if (alerta.evaluar(ultimoClima)) {
                emailService.enviarAlertas(ultimoClima, alerta.getTipoAlerta());
            }
        }
        idUltimoClimaProcesado = ultimoClima.getId();
    }
}
