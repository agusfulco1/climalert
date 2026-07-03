package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.entities.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepository {

    Optional<Clima> findById(Long id);

    void save(Clima clima);

    List<Clima> findAll();
}
