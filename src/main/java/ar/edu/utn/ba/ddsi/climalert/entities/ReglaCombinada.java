package ar.edu.utn.ba.ddsi.climalert.entities;

public class ReglaCombinada extends ReglaClimatica {

    public ReglaCombinada() {
        super(TipoAlerta.COMBINADA);
    }

    @Override
    public Boolean evaluar(Clima clima){
        return clima.getTemperatura() > 35 && clima.getHumedad() > 60;
    }
}
