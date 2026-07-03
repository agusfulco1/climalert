package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaResponseDTO;

import java.util.List;

public interface ClimaService {
    List<ClimaResponseDTO> findAll();

    void getClima();

    void evaluarClima();
}
