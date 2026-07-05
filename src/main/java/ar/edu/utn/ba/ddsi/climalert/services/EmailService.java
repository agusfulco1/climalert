package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.entities.TipoAlerta;

public interface EmailService {
    void enviarAlertas(Clima clima, TipoAlerta tipoAlerta);
}
