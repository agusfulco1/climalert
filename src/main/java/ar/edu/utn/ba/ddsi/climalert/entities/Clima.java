package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clima {
    private Long id;
    private Double temperatura;
    private Double humedad;
    private LocalDateTime fechaGeneracion;
    private String ciudad;
    private String pais;
}
