package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaDTO;

import java.util.List;

public interface ClimaService {
    List<ClimaDTO> findAll();

    void getClima();
}
