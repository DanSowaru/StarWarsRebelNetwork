package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.*;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.enums.Item;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReportSummaryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReturnRebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
import br.com.letscode.starwarsrebelnetwork.dto.response.ResourcesReportDTO;
import br.com.letscode.starwarsrebelnetwork.enums.RebelStatus;
import br.com.letscode.starwarsrebelnetwork.service.RebelReportService;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import br.com.letscode.starwarsrebelnetwork.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/rebels")
public class RebelRestController {

    private final RebelService rebelService;
    private final RebelReportService reportService;
    private final RebelRepository repository;
    private final TradeService tradeService;

    public RebelRestController(RebelService rebelService, RebelReportService reportService, RebelRepository repository, TradeService tradeService) {
        this.rebelService = rebelService;
        this.reportService = reportService;
        this.repository = repository;
        this.tradeService = tradeService;
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

    @GetMapping("/resources")
    public ResourcesReportDTO getResourcesInformation() {
        return reportService.getResourcesReport();
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

    @PostMapping("/trade/{firstId}/{secondId}")

    public void postInventoryRebel(@PathVariable String firstId,
                                         @PathVariable String secondId,
                                         @RequestBody RebelInventoryTradeDTO rebelInventoryTradeDTO){

        RebelEntity firstRebel = tradeService.firstRebel(firstId);
        RebelEntity secondRebel = tradeService.secondRebel(secondId);


        List<InventoryItemEntity> firstRebelItens = tradeService.firstRebelItens(firstRebel);
        List<InventoryItemEntity> secondRebelItens = tradeService.secondRebelItens(secondRebel);

        List<InventoryItemDTO> firstRebelTradeItens = rebelInventoryTradeDTO.getFirstRebelOffer();
        List<InventoryItemDTO> secondRebelTradeItens = rebelInventoryTradeDTO.getSecondRebelOffer();

        final int firstRebelOfferValue = tradeService.firstRebelOfferValueSum(firstRebelTradeItens);
        final int secondRebelOfferValue = tradeService.secondRebelOfferValueSum(secondRebelTradeItens);
        tradeService.isOfferEquals(firstRebelOfferValue, secondRebelOfferValue);


        tradeService.firstRebelHashMap(firstRebelItens);
        tradeService.secondRebelHashMap(secondRebelItens);

        tradeService.subtractOfferedItensFirstRebel(firstRebelTradeItens);


        tradeService.addOfferedItensSecondRebelInFirstRebelInventory(secondRebelTradeItens);

        tradeService.subtractOfferedItensSecondRebel(secondRebelTradeItens);
        tradeService.addOfferedItensFirstRebelInSecondRebelInventory(firstRebelTradeItens);




        List<InventoryItemEntity> finalFirstRebelInventory = tradeService.firstRebelInventoryToInventoryEntity();
        List<InventoryItemEntity> finalSecondRebelInventory = tradeService.secondRebelInventoryToInventoryEntity();
        firstRebel.getInventory().setItensEntity(finalFirstRebelInventory);
        secondRebel.getInventory().setItensEntity(finalSecondRebelInventory);
    }
}
