package br.com.letscode.starwarsrebelnetwork.entity;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.Data;


@Data
public class InventoryItemEntity {


    private Item item;
    private int quantity;
}
