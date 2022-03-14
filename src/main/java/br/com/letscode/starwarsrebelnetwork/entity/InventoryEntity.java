package br.com.letscode.starwarsrebelnetwork.entity;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {

    private List<InventoryItemEntity> itensEntity;

    public List<InventoryItemEntity> getAll() {
        return itensEntity;
    }

    public int getPoints() {
        return itensEntity.stream().mapToInt(resource -> resource.getItem().getPrice() * resource.getQuantity()).sum();
    }

    public int getQuantityByItem(Item item) {
        return itensEntity.stream().filter(resource -> resource.getItem().equals(item)).findFirst().get().getQuantity();
    }
}
