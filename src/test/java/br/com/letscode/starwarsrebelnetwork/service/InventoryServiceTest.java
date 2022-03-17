package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.request.InventoryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.InventoryItemDTO;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryEntity;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import br.com.letscode.starwarsrebelnetwork.enums.Item;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    InventoryService service = new InventoryService();

    @Test
    void inventoryDTOToInventoryEntity() {
        var inventoryDTO = new InventoryDTO();

        var firstItem = new InventoryItemDTO(Item.AMMO,1);
        var secondItem = new InventoryItemDTO(Item.FOOD,2);

        inventoryDTO.setItens(Arrays.asList(firstItem,secondItem));

        var response = service.inventoryDTOToInventoryEntity(inventoryDTO);

        assertEquals(response.getItensEntity().get(0).getItem(),firstItem.getItem());
        assertEquals(response.getItensEntity().get(0).getQuantity(),firstItem.getQuantity());
        assertEquals(response.getItensEntity().get(1).getItem(),secondItem.getItem());
        assertEquals(response.getItensEntity().get(1).getQuantity(),secondItem.getQuantity());

    }


    @Test
    void returnInventoryItemEntityToInventoryItemDTO() {
        var firstItem = new InventoryItemEntity(Item.AMMO,1);
        var secondItem = new InventoryItemEntity(Item.FOOD,2);

        InventoryEntity inventoryEntity = new InventoryEntity(Arrays.asList(firstItem, secondItem));


        var response = service.mapInventoryEntityToInventoryDTO(inventoryEntity);

        assertEquals(response.getItens().get(0).getItem(),firstItem.getItem());
        assertEquals(response.getItens().get(0).getQuantity(),firstItem.getQuantity());
        assertEquals(response.getItens().get(1).getItem(),secondItem.getItem());
        assertEquals(response.getItens().get(1).getQuantity(),secondItem.getQuantity());

    }

}