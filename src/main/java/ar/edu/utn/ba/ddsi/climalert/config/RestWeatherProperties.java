package ar.edu.utn.ba.ddsi.climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weatherapi")
@Data
public class RestWeatherProperties {
    private String url;
    private String key;
    private String location;
}
