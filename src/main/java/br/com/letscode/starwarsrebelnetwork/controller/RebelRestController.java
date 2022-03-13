package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReportSummaryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReturnRebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
import br.com.letscode.starwarsrebelnetwork.enums.RebelStatus;
import br.com.letscode.starwarsrebelnetwork.service.RebelReportService;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rebels")
public class RebelRestController {

    private final RebelService rebelService;
    private final RebelReportService reportService;

    public RebelRestController(RebelService rebelService, RebelReportService reportService) {
        this.rebelService = rebelService;
        this.reportService = reportService;
    }

    @GetMapping("/all")
    public List<ReturnRebelDTO> getRebelsList() {
        return rebelService.listAllRebels();
    }

    @GetMapping("/{id}")
    public ReturnRebelDTO getRebelDTO(@PathVariable("id") String id) {
        return rebelService.getRebelById(id);
    }

    @GetMapping("/traitors")
    public ReportSummaryDTO getTraitorsInformation() {
        return reportService.getSummaryReport(RebelStatus.TRAITOR);
    }

    @GetMapping("/allies")
    public ReportSummaryDTO getAlliesInformation() {
        return reportService.getSummaryReport(RebelStatus.ALLY);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newRebel(@RequestBody RebelDTO rebelDTO) {

        this.rebelService.newRebel(rebelDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void accuseRebel(@RequestBody String rebelID) {

        this.rebelService.accuseRebel(rebelID);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReturnRebelDTO> patchRebelLocation(@PathVariable String id, @RequestBody RebelPatchLocationRequestDTO rebelPatchLocationRequestDTO) {

        ReturnRebelDTO rebelDTO = rebelService.update(id, rebelPatchLocationRequestDTO);

        return ResponseEntity.ok(rebelDTO);
    }
}
