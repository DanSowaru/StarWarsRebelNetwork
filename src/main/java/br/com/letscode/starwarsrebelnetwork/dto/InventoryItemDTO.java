package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.Data;


@Data
public class InventoryItemDTO {

    private Item item;
    private int quantity;



}
