package br.com.letscode.starwarsrebelnetwork.controller;

import br.com.letscode.starwarsrebelnetwork.dto.*;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
import br.com.letscode.starwarsrebelnetwork.enums.Item;
import br.com.letscode.starwarsrebelnetwork.service.RebelReportService;
import br.com.letscode.starwarsrebelnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void postInventoryRebel(@PathVariable String firstId,
                                                                    @PathVariable String secondId,
                                                                    @RequestBody RebelInventoryTradeDTO rebelInventoryTradeDTO) {

        ReturnRebelDTO firstRebel = rebelService.getRebelById(firstId);
        ReturnRebelDTO secondRebel = rebelService.getRebelById(secondId);

        //firstRebelItens = ["AMMO",2, "WEAPON", 1, "WATER", 3]
        //firstRebelTradeItens = ["WATER", 2]

        List<InventoryItemDTO> firstRebelItens = firstRebel.getInventory().getItens(); // PEGANDO TODOS OS ITENS DO INVENTARIO DO PRIMEIRO REBELDE
        List<InventoryItemDTO> secondRebelItens = secondRebel.getInventory().getItens();

        List<InventoryItemDTO> firstRebelTradeItens = rebelInventoryTradeDTO.getFirstRebelOffer();
        List<InventoryItemDTO> secondRebelTradeItens = rebelInventoryTradeDTO.getSecondRebelOffer();
        // PEGANDO A LISTA DE ITENS PASSADA NO PARAMETRO DA FUNCAO,
        //OU SEJA, OS ITENS A SEREM TROCADOS

        final int firstRebelOfferValue = firstRebelTradeItens.stream()
                // From InventoryItemDTO to int Value
                //PEGANDO CADA ITEM A SER TROCADO, OLHANDO O PREÇO, MULTIPLICANDO PELA QUANTIDADE E SOMANDO PARA UM TOTAL.
                .map(offerItem -> offerItem.getItem().getPrice() * offerItem.getQuantity())
                .mapToInt(item -> item)
                .sum();

        final int secondRebelOfferValue = secondRebelTradeItens.stream()
                // From InventoryItemDTO to int Value
                //PEGANDO CADA ITEM A SER TROCADO, OLHANDO O PREÇO, MULTIPLICANDO PELA QUANTIDADE E SOMANDO PARA UM TOTAL.
                .map(offerItem -> offerItem.getItem().getPrice() * offerItem.getQuantity())
                .mapToInt(item -> item)
                .sum();

        //CONDIÇÃO PARA VER SE OS VALORES SÃO IGUAIS PARA A TROCA
//        if (firstRebelOfferValue != secondRebelOfferValue) {
//            return "BAD REQUEST, troca inválida";
//        }

        //CONSTRUIR UM NOVO HASHMAP PARA GUARDAR OS ITENS DO REBELDE QUE PEGAMOS POR ID firstRebel;
        //ESSA LISTA É A QUE IREMOS TRABALHAR EM CIMA

        Map<Item, Integer> firstRebelInventory = new HashMap<Item, Integer>();
        for(InventoryItemDTO inventoryItem: firstRebelItens){
            firstRebelInventory.put(inventoryItem.getItem(), inventoryItem.getQuantity());
        }

        //COM A LISTA CRIADA, AGORA É REMOVER OS ITENS DA OFFER. DO PRIMEIRO REBELDE
        //PEGO A LISTA DE ITENS OFERTADAS ( QUE É PASSADA NO POST ) E PARA CADA ITEM
        // EU RELACIONO COM A LISTA QUE CRIEI DO INVENTARIO (O MAP) DO REBELDE QUE PEGAMOS POR
        // ID. AQUI ESTOU APENAS TIRANDO OS ITENS OFERTADOS
        firstRebelTradeItens.forEach(tradeItem -> {
            final Item item = tradeItem.getItem();

            int totalQuantity = firstRebelInventory.get(item);
            int tradeQuantity = tradeItem.getQuantity();

            int newQuantity = totalQuantity - tradeQuantity;

            //AQUI EU DOU UPDATE NO MAP DO INVENTARIO DO PRIMEIRO REBELDE FILTRADO
            firstRebelInventory.put(item, newQuantity);
        });



        //CONSTRUIR UM MAPA PARA O SEGUNDO REBELDE FILTRADO


        //ADICIONANDO OS ITENS DA SEGUNDA OFERTA NO MAP DO PRIMEIRO REBELDE FILTRADO
        secondRebelTradeItens.forEach(tradeItem ->{
            final Item receivedItem = tradeItem.getItem();

            if(firstRebelInventory.containsKey(receivedItem)) {
                int receivedQuantity = tradeItem.getQuantity();
                int previousQuantity = firstRebelInventory.get(receivedItem);

                int newQuantity = previousQuantity + receivedQuantity;

                //UPDATE DO MAP DO PRIMEIRO REBELDE
                firstRebelInventory.put(receivedItem, newQuantity);
            } else {
                firstRebelInventory.put(receivedItem, tradeItem.getQuantity());
            }
        });


        //AGORA APLICO A MESMA LOGICA PARA O SEGUNDO REBELDE COM A OFERTA DO PRIMEIRO
        Map<Item, Integer> secondRebelInventory = new HashMap<Item,Integer>();
        for(InventoryItemDTO inventoryItem: secondRebel.getInventory().getItens()) {
            secondRebelInventory.put(inventoryItem.getItem(), inventoryItem.getQuantity());
        }

        //AGORA REMOVO OS ITENS OFERTADOS DA LISTA MAP
        secondRebelTradeItens.forEach(tradeItem -> {
            final Item item = tradeItem.getItem();

            int totalQuantity = secondRebelInventory.get(item);

            int tradeQuantity = tradeItem.getQuantity();

            int newQuantity = totalQuantity - tradeQuantity;

            //UPDATE MAP FOR SECOND REBEL
            secondRebelInventory.put(item, newQuantity);
        });


        //AGORA ADICIONO A OFERTA
        firstRebelTradeItens.forEach(tradeItem -> {
            final Item receivedItem = tradeItem.getItem();

            if(secondRebelInventory.containsKey(receivedItem)) {
                int receivedQuantity = tradeItem.getQuantity();
                int previousQuantity = secondRebelInventory.get(receivedItem);

                int newQuantity = previousQuantity + receivedQuantity;

                //UPDATE DO MAP
                secondRebelInventory.put(receivedItem, newQuantity);
            } else {
                secondRebelInventory.put(receivedItem, tradeItem.getQuantity());
            }
        });

        //SALVAR OS NOVOS MAPS(INVENTARIOS) PARA O REPOSITORIO ONDE TEM ENTITY
        final List<InventoryItemDTO> firstRebelFinalItems = firstRebelInventory.entrySet().stream().map(entry ->{
            final InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();

            inventoryItemDTO.setItem(entry.getKey());
            inventoryItemDTO.setQuantity(entry.getValue());
            return inventoryItemDTO;
        }).collect(Collectors.toList());

        final List<InventoryItemDTO> secondRebelFinalItems = secondRebelInventory.entrySet().stream().map(entry -> {
            final InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();

            inventoryItemDTO.setItem(entry.getKey());
            inventoryItemDTO.setQuantity(entry.getValue());
            return inventoryItemDTO;
        }).collect(Collectors.toList());

        firstRebel.getInventory().setItens(firstRebelFinalItems);
        secondRebel.getInventory().setItens(secondRebelFinalItems);









//        List<InventoryItemDTO> finalFirstRebelInventory = firstRebelItens.stream().map(item -> {
//            Optional<InventoryItemDTO> itemToTrade = firstRebelTradeItens.stream().filter(tradeItem -> tradeItem.getItem().equals(item.getItem())).findFirst();
//            if (itemToTrade.isEmpty()) {
//                return item;
//            } else {
//                int quantity = item.getQuantity();
//                int quantityToTrade = itemToTrade.get().getQuantity();
//
//                int finalQuantity = quantity - quantityToTrade;
//                InventoryItemDTO inventory = new InventoryItemDTO();
//                inventory.setItem(item.getItem());
//                inventory.setQuantity(finalQuantity);
//                return inventory;
//            }
//
//        }).collect(Collectors.toList());


//        System.out.println("hello");
//        return ResponseEntity.ok(null);

    }
}
