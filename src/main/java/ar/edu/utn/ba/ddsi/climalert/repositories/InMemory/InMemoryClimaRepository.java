package ar.edu.utn.ba.ddsi.climalert.repositories.InMemory;

import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.exceptions.AlreadyExists;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert.utils.GeneradorIdSecuencial;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClimaRepository implements ClimaRepository {

    private final List<Clima> climas = new ArrayList<>();
    private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

    @Override
    public Optional<Clima> findById(Long id){
        return climas.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Clima clima){
        if (clima.getId() == null){
            clima.setId(generadorId.siguiente());
            climas.add(clima);
        } else {
            throw new AlreadyExists("Este clima ya existe");
        }
    }

    @Override
    public List<Clima> findAll(){
        return new ArrayList<>(climas);
    }

    @Override
    public Clima findLast() {
        if (climas.isEmpty()) return null;
        return climas.getLast();
    }
}
