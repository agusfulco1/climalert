package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Alerta {
    private Long id;
    private Clima clima;
    private TipoAlerta tipoAlerta;
    private LocalDateTime fechaGeneracion;
}
