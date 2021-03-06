package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.request.InventoryItemDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelInventoryTradeDTO;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.response.ReportSummaryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.response.ReturnRebelDTO;
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
    private final TradeService tradeService;

    public RebelRestController(RebelService rebelService, RebelReportService reportService, TradeService tradeService) {
        this.rebelService = rebelService;
        this.reportService = reportService;
        this.tradeService = tradeService;
    }

    @GetMapping("/all")
    public List<ReturnRebelDTO> getRebelsList() {
        return rebelService.listAllRebels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnRebelDTO> getRebelDTO(@PathVariable("id") String id) {
        return new ResponseEntity(rebelService.getRebelById(id), HttpStatus.OK);
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
    public ReturnRebelDTO newRebel(@RequestBody RebelDTO rebelDTO) {

        return this.rebelService.newRebel(rebelDTO);
    }

    @PatchMapping("/accusation")
    @ResponseStatus(HttpStatus.OK)
    public void accuseRebel(@RequestBody String rebelID) {

        this.rebelService.accuseRebel(rebelID);
    }

    @PatchMapping("/location/{id}")
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

        tradeService.checkTransactionIds(firstRebel, secondRebel);
        tradeService.checkTraitors(firstRebel, secondRebel);

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
