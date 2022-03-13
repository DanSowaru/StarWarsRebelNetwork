package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemDTO {

    private Item item;
    private int quantity;
}
