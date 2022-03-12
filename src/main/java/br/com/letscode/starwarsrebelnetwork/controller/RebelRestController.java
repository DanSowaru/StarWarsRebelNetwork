package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.*;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
import br.com.letscode.starwarsrebelnetwork.service.RebelReportService;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ReturnTraitorsDTO getTraitorsInformation() {
        return reportService.getTraitorsReport();
    }

    @GetMapping("/allies")
    public ReturnAlliesDTO getAlliesInformation() {
        return reportService.getAlliesReport();
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
    //ResponseEntity<RebelInventoryTradeDTO>
    public void patchInventoryRebel(@PathVariable String firstId,
                                                                    @PathVariable String secondId,
                                                                    @RequestBody RebelInventoryTradeDTO rebelInventoryTradeDTO) {

        ReturnRebelDTO firstRebel = rebelService.getRebelById(firstId);
        ReturnRebelDTO secondRebel = rebelService.getRebelById(secondId);

        //firstRebelItens = ["AMMO",2, "WEAPON", 1, "WATER", 3]
        //firstRebelTradeItens = ["WATER", 2]

        List<InventoryItemDTO> firstRebelItens = firstRebel.getInventory().getItens();
        List<InventoryItemDTO> firstRebelTradeItens = rebelInventoryTradeDTO.getFirstRebelOffer();
        List<InventoryItemDTO> finalFirstRebelInventory = firstRebelItens.stream().map(item -> {
            Optional<InventoryItemDTO> itemToTrade = firstRebelTradeItens.stream().filter(tradeItem -> tradeItem.getItem().equals(item.getItem())).findFirst();
            if (itemToTrade.isEmpty()) {
                return item;
            } else {
                int quantity = item.getQuantity();
                int quantityToTrade = itemToTrade.get().getQuantity();

                int finalQuantity = quantity - quantityToTrade;
                InventoryItemDTO inventory = new InventoryItemDTO();
                inventory.setItem(item.getItem());
                inventory.setQuantity(finalQuantity);
                return inventory;
            }

        }).collect(Collectors.toList());


//        System.out.println("hello");
//        return ResponseEntity.ok(null);

    }
}
