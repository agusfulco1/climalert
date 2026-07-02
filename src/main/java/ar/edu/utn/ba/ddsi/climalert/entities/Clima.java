package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Clima {
    private Long id;
    private Double temperatura;
    private Double humedad;
    private LocalDateTime fechaGeneracion;
    private String condicion;

}
