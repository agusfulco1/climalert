package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaRequestDTO;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaResponseDTO;
import ar.edu.utn.ba.ddsi.climalert.entities.Clima;

import java.util.List;

public interface ClimaService {
    List<ClimaResponseDTO> findAll();

    void getClima();

    Clima obtenerUltimoClima();
}
