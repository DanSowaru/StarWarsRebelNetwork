package br.com.letscode.starwarsrebelnetwork.entity;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemEntity {


    private Item item;
    private int quantity;
}
