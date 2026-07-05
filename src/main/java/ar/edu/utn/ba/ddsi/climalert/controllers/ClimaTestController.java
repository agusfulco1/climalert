package ar.edu.utn.ba.ddsi.climalert.controllers;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaResponseDTO;
import ar.edu.utn.ba.ddsi.climalert.entities.TipoAlerta;
import ar.edu.utn.ba.ddsi.climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test/climas")
public class ClimaTestController {

    private final ClimaService climaService;
    private final AlertaService alertaService;

    public ClimaTestController(ClimaService climaService, AlertaService alertaService) {
        this.climaService = climaService;
        this.alertaService = alertaService;
    }

    @GetMapping
    public List<ClimaResponseDTO> getAll() {
        return climaService.findAll();
    }
}
