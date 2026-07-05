package ar.edu.utn.ba.ddsi.climalert.services.Impl;

import ar.edu.utn.ba.ddsi.climalert.config.RestWeatherProperties;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaRequestDTO;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaResponseDTO;
import ar.edu.utn.ba.ddsi.climalert.dtos.Localidad;
import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.exceptions.ClimaException;
import ar.edu.utn.ba.ddsi.climalert.exceptions.NullException;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaServiceImpl implements ClimaService {
    private final RestTemplate restTemplate;
    private final RestWeatherProperties propiedades;
    private final ClimaRepository climaRepository;

    public ClimaServiceImpl(RestTemplate restTemplate, RestWeatherProperties propiedades, ClimaRepository climaRepository) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
        this.climaRepository = climaRepository;
    }
    @Override
    public void getClima() {
        try {
            URI uri =
                    UriComponentsBuilder.fromUriString(propiedades.getUrl() + "/current.json")
                            .queryParam("key", propiedades.getKey())
                            .queryParam("q", propiedades.getLocation())
                            .build()
                            .toUri();
            ClimaRequestDTO cuerpo = restTemplate.getForObject(uri, ClimaRequestDTO.class);
            if (cuerpo != null && cuerpo.getClimaActual() != null) {
                Clima clima = new Clima(null,cuerpo.getClimaActual().getTemperatura(),cuerpo.getClimaActual().getHumedad(), LocalDateTime.now(),cuerpo.getLocation().getCiudad(),cuerpo.getLocation().getPais());
                climaRepository.save(clima);
            } else {
                throw new NullException("No se pudo extrar el cuerpo de la request");
            }
        }
        catch (Exception e){
            throw new ClimaException("Error en el get a la api");
        }

    }
    @Override
    public List<ClimaResponseDTO> findAll(){
        return climaRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Clima obtenerUltimoClima() {
        return climaRepository.findLast();
    }

    public ClimaResponseDTO toDto(Clima clima){
        Localidad localidad = new Localidad(clima.getCiudad(),clima.getPais());
        ClimaActual actual = new ClimaActual(clima.getTemperatura(),clima.getHumedad());

        return new ClimaResponseDTO(clima.getId(),localidad, actual, clima.getFechaGeneracion(), null);
    }
}