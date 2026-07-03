package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.config.RestWeatherProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ClimaServices {
    private final RestTemplate restTemplate;
    private final RestWeatherProperties propiedades;

    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.location}")
    private String location;

    public ClimaServices(RestTemplate restTemplate, RestWeatherProperties propiedades) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
    }

    @Scheduled(fixedRate = 30000)
    public void getClima() {
        URI uri =
                UriComponentsBuilder.fromUriString(propiedades.getUrl())
                        .queryParam("key", propiedades.getKey())
                        .queryParam("q", propiedades.getLocation())
                        .build()
                        .toUri();

    }
}
