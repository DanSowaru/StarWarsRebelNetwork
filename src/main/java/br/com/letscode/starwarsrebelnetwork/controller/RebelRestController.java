package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rebels")
public class RebelRestController {

    private final RebelService rebelService;

    public RebelRestController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @GetMapping
    public List<RebelDTO> getRebelsList() {
        return rebelService.listAllRebels();
    }
}
