package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.config.RestWeatherProperties;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaDTO;
import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class ClimaServices {
    private final RestTemplate restTemplate;
    private final RestWeatherProperties propiedades;
    private final ClimaRepository climaRepository;

    public ClimaServices(RestTemplate restTemplate, RestWeatherProperties propiedades, ClimaRepository climaRepository) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
        this.climaRepository = climaRepository;
    }

    public ClimaDTO getClima() {
        URI uri =
                UriComponentsBuilder.fromUriString(propiedades.getUrl())
                        .queryParam("key", propiedades.getKey())
                        .queryParam("q", propiedades.getLocation())
                        .build()
                        .toUri();
        ClimaDTO cuerpo = restTemplate.getForObject(uri, ClimaDTO.class);
        if (cuerpo != null && cuerpo.getClimaActual() != null) {
            Clima clima = new Clima(null,cuerpo.getClimaActual().getTemperatura(),cuerpo.getClimaActual().getHumedad(),LocalDateTime.now(),cuerpo.getLocation().getCiudad(),cuerpo.getLocation().getPais());
            climaRepository.save(clima);
            return cuerpo;
        } else {
            //TODO: Manejo de errores
            return null;
        }

    }
}
