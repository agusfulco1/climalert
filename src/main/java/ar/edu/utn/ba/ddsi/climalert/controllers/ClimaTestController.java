package ar.edu.utn.ba.ddsi.climalert.controllers;
import ar.edu.utn.ba.ddsi.climalert.dtos.ClimaDTO;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test/climas")
public class ClimaTestController {

    private final ClimaService climaService;

    public ClimaTestController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping
    public List<ClimaDTO> getAll() {
        return climaService.findAll();
    }
}
