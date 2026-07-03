package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.config.RestWeatherProperties;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaRequestDTO;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaResponseDTO;
import ar.edu.utn.ba.ddsi.climalert.dtos.Localidad;
import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaServiceImpl implements  ClimaService{
    private final RestTemplate restTemplate;
    private final RestWeatherProperties propiedades;
    private final ClimaRepository climaRepository;

    public ClimaServiceImpl(RestTemplate restTemplate, RestWeatherProperties propiedades, ClimaRepository climaRepository) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
        this.climaRepository = climaRepository;
    }

    public void getClima() {
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
            //TODO: Manejo de errores
        }
    }

    public List<ClimaResponseDTO> findAll(){
        return climaRepository.findAll().stream().map(this::toDto).toList();
    }

    public ClimaResponseDTO toDto(Clima clima){
        Localidad localidad = new Localidad(clima.getCiudad(),clima.getPais());
        ClimaActual actual = new ClimaActual(clima.getTemperatura(),clima.getHumedad());

        return new ClimaResponseDTO(clima.getId(),localidad, actual, clima.getFechaGeneracion());
    }

    public void evaluarClima(){

    }
}