package ar.edu.utn.ba.ddsi.climalert.dtos;

import ar.edu.utn.ba.ddsi.climalert.entities.TipoAlerta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClimaResponseDTO {
    private long id;
    private Localidad localidad;
    private ClimaActual actual;
    private LocalDateTime fechaGeneracion;
    private TipoAlerta tipoAlerta;

}
