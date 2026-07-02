package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.Data;

@Data
public abstract class ReglaClimatica {
    private TipoAlerta tipoAlerta;

    public ReglaClimatica(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public abstract Boolean evaluar(Clima clima);
}
