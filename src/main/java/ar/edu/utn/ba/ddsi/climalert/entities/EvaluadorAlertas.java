package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.Data;

@Data
public abstract class EvaluadorAlertas {
    private TipoAlerta tipoAlerta;

    public EvaluadorAlertas(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public abstract Boolean evaluar(Clima clima);
}
