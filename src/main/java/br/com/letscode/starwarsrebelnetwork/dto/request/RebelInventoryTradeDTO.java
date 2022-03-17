package br.com.letscode.starwarsrebelnetwork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@Setter
@AllArgsConstructor
public class RebelInventoryTradeDTO {

    private List<InventoryItemDTO> firstRebelOffer;
    private List<InventoryItemDTO> secondRebelOffer;
}
