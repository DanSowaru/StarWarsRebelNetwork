package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.InventoryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.InventoryItemDTO;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryEntity;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {


    private InventoryItemDTO inventoryItemDTO;


    public InventoryEntity inventoryDTOToInventoryEntity(InventoryDTO inventoryDTO) {

        List<InventoryItemEntity> collect = inventoryDTO.getAll().stream()
                .map((this::inventoryItemDTOToInventoryItemEntity))
                .collect(Collectors.toList());
        InventoryEntity entity = new InventoryEntity();
        entity.setItensEntity(collect);

        return entity;
    }


    public InventoryItemEntity inventoryItemDTOToInventoryItemEntity(InventoryItemDTO inventoryItemDTO) {
        InventoryItemEntity entity = new InventoryItemEntity();

        entity.setQuantity(inventoryItemDTO.getQuantity());
        entity.setItem(inventoryItemDTO.getItem());
        return entity;

    }

    public InventoryItemDTO mapInventoryItemEntityToInventoryItemDTO(InventoryItemEntity inventoryItemEntity) {
        InventoryItemDTO dto = new InventoryItemDTO();

        dto.setQuantity(inventoryItemEntity.getQuantity());
        dto.setItem(inventoryItemEntity.getItem());

        return dto;
    }

    public InventoryDTO mapInventoryEntityToInventoryDTO(InventoryEntity inventoryEntity) {
        List<InventoryItemDTO> collect = inventoryEntity.getAll().stream()
                .map((this::mapInventoryItemEntityToInventoryItemDTO))
                .collect(Collectors.toList());

        return new InventoryDTO(collect);
    }
}




