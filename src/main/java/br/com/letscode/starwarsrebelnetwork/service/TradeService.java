package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.request.InventoryItemDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelInventoryTradeDTO;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.enums.Item;
import br.com.letscode.starwarsrebelnetwork.exceptions.NotEqualTradeSumException;
import br.com.letscode.starwarsrebelnetwork.exceptions.SameIdTradeException;
import br.com.letscode.starwarsrebelnetwork.exceptions.TraitorTradeException;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TradeService {

    private final RebelRepository repository;
    private final RebelInventoryTradeDTO rebelInventoryTradeDTO;
    public final HashMap<Item, Integer> firstRebelInventory;
    public final HashMap<Item, Integer> secondRebelInventory;


    public void checkTransactionIds(RebelEntity firstRebel, RebelEntity secondRebel) {
        if (firstRebel.getId().equals(secondRebel.getId())) {
            throw new SameIdTradeException("The trade cannot be completed. A Rebel cannot trade with HIMSELF!");
        }
    }

    public void checkTraitors(RebelEntity firstRebel, RebelEntity secondRebel) {
        if ((firstRebel.getAccusations() > 2) || (secondRebel.getAccusations() > 2)) {
            throw new TraitorTradeException("The trade cannot be completed. Rebel tagged as TRAITOR!", firstRebel.getId(), secondRebel.getId(), firstRebel.getAccusations(), secondRebel.getAccusations());
        }
    }

    public RebelEntity firstRebel(String firstId) {
        RebelEntity firstRebel = repository.getRebel(firstId);

        return firstRebel;
    }

    public List<InventoryItemDTO> getFirstRebelTradeItens () {
       List<InventoryItemDTO> firstRebelTradeItens = rebelInventoryTradeDTO.getFirstRebelOffer();
        return firstRebelTradeItens;
    }

    public List<InventoryItemEntity> firstRebelItens (RebelEntity firstRebel) {
        List<InventoryItemEntity> firstRebelItens = firstRebel.getInventory().getItensEntity();
        return firstRebelItens;
    }

    public int firstRebelOfferValueSum (List<InventoryItemDTO> firstRebelTradeItens) {
        int firstRebelOffer = firstRebelTradeItens.stream()
                // From InventoryItemDTO to int Value
                //PEGANDO CADA ITEM A SER TROCADO, OLHANDO O PREÇO, MULTIPLICANDO PELA QUANTIDADE E SOMANDO PARA UM TOTAL.
                .map(offerItem -> offerItem.getItem().getPrice() * offerItem.getQuantity())
                .mapToInt(item -> item)
                .sum();
        return firstRebelOffer;
    }

    public RebelEntity secondRebel (String secondId){
        RebelEntity secondRebel = repository.getRebel(secondId);

        return secondRebel;
    }

    public List<InventoryItemDTO> getSecondRebelTradeItens () {
        List<InventoryItemDTO> secondRebelTradeItens = rebelInventoryTradeDTO.getSecondRebelOffer();
        return secondRebelTradeItens;
    }

    public List<InventoryItemEntity> secondRebelItens(RebelEntity secondRebel) {
        List<InventoryItemEntity> secondRebelItens = secondRebel.getInventory().getItensEntity();
        return secondRebelItens;
    }

    public int secondRebelOfferValueSum (List<InventoryItemDTO> secondRebelTradeItens) {
        int secondRebelOffer = secondRebelTradeItens.stream()
                // From InventoryItemDTO to int Value
                //PEGANDO CADA ITEM A SER TROCADO, OLHANDO O PREÇO, MULTIPLICANDO PELA QUANTIDADE E SOMANDO PARA UM TOTAL.
                .map(offerItem -> offerItem.getItem().getPrice() * offerItem.getQuantity())
                .mapToInt(item -> item)
                .sum();
        return secondRebelOffer;
    }

    public HttpStatus isOfferEquals(int firstRebelOfferValueSum, int secondRebelOfferValueSum){
        if(firstRebelOfferValueSum != secondRebelOfferValueSum) {
                throw new NotEqualTradeSumException("Offer values don't match. The trade cannot be completed!", firstRebelOfferValueSum, secondRebelOfferValueSum);
        } else {
                return null;
            }
    };

    public void firstRebelHashMap (List<InventoryItemEntity> firstRebelInventoryEntity) {
        for(InventoryItemEntity inventoryItem: firstRebelInventoryEntity){
            firstRebelInventory.put(inventoryItem.getItem(), inventoryItem.getQuantity());
        }
    }

    public void secondRebelHashMap (List<InventoryItemEntity> secondRebelInventoryEntity) {
        for(InventoryItemEntity inventoryItem: secondRebelInventoryEntity) {
            secondRebelInventory.put(inventoryItem.getItem(), inventoryItem.getQuantity());
        }
    }

    public void subtractOfferedItensFirstRebel (List<InventoryItemDTO> firstRebelOfferedItens) {
        firstRebelOfferedItens.forEach(tradeItem -> {
            final Item item = tradeItem.getItem();

            int totalQuantity = firstRebelInventory.get(item);
            int tradeQuantity = tradeItem.getQuantity();

            int newQuantity = totalQuantity - tradeQuantity;
            //AQUI EU DOU UPDATE NO MAP DO INVENTARIO DO PRIMEIRO REBELDE FILTRADO
            firstRebelInventory.put(item, newQuantity);
        });

    }

    public void addOfferedItensSecondRebelInFirstRebelInventory (List<InventoryItemDTO> secondRebelOfferItens) {
        secondRebelOfferItens.forEach(tradeItem -> {

            final Item receivedItem = tradeItem.getItem();
            if(firstRebelInventory.containsKey(receivedItem)) {
                int receivedQuantity = tradeItem.getQuantity();
                int previousQuantity = firstRebelInventory.get(receivedItem);

                int newQuantity = previousQuantity + receivedQuantity;

                //UPDATE DO MAP
                firstRebelInventory.put(receivedItem, newQuantity);
            } else {
                firstRebelInventory.put(receivedItem, tradeItem.getQuantity());
            }
        });

    }

    public void subtractOfferedItensSecondRebel (List<InventoryItemDTO> secondRebelOfferedItens) {
        secondRebelOfferedItens.forEach(tradeItem -> {
            final Item item = tradeItem.getItem();

            int totalQuantity = secondRebelInventory.get(item);
            int tradeQuantity = tradeItem.getQuantity();

            int newQuantity = totalQuantity - tradeQuantity;
            //AQUI EU DOU UPDATE NO MAP DO INVENTARIO DO PRIMEIRO REBELDE FILTRADO
            secondRebelInventory.put(item, newQuantity);
        });
    }

    public void addOfferedItensFirstRebelInSecondRebelInventory (List<InventoryItemDTO> firstRebelOfferItens) {
        firstRebelOfferItens.forEach(tradeItem -> {
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
    }

    public List<InventoryItemEntity> firstRebelInventoryToInventoryEntity() {
        List<InventoryItemEntity> firstRebelInventoryEntity = firstRebelInventory.entrySet().stream().map(entry ->{
            final InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();

            inventoryItemEntity.setItem(entry.getKey());
            inventoryItemEntity.setQuantity(entry.getValue());
            return inventoryItemEntity;
        }).collect(Collectors.toList());
        return firstRebelInventoryEntity;
    }

    public List<InventoryItemEntity> secondRebelInventoryToInventoryEntity() {
        List<InventoryItemEntity> secondRebelInventoryEntity = secondRebelInventory.entrySet().stream().map(entry -> {
            final InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();

            inventoryItemEntity.setItem(entry.getKey());
            inventoryItemEntity.setQuantity(entry.getValue());
            return inventoryItemEntity;
        }).collect(Collectors.toList());
        return secondRebelInventoryEntity;
    }

}
