package br.com.letscode.starwarsrebelnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RebelInventoryTradeDTO {

    private List<InventoryItemDTO> firstRebelOffer;
    private List<InventoryItemDTO> secondRebelOffer;
}
