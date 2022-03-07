package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rebels")
public class RebelRestController {

    private final RebelService rebelService;

    public RebelRestController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @GetMapping("/all")
    public List<RebelEntity> getRebelsList() {
        return rebelService.listAllRebels();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newRebel(@RequestBody RebelDTO rebelDTO) {

        this.rebelService.newRebel(rebelDTO);
    }
}
