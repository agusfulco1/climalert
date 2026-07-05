package ar.edu.utn.ba.ddsi.climalert.entities;

import org.springframework.stereotype.Component;

@Component //La carga en memoria al iniciar el programa, sin tener que instanciarla una y otra vez
public class AlertaCombinada extends EvaluadorAlertas {

    public AlertaCombinada() {
        super(TipoAlerta.COMBINADA);
    }

    @Override
    public Boolean evaluar(Clima clima){
        return clima.getTemperatura() > -10 && clima.getHumedad() > 0;
    }
}
