package ar.edu.utn.ba.ddsi.climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "climalert")
@Data
public class RestEmailProperties {
    private List<String> destinatarios;
    private String username;
}
