package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {


    private List<InventoryItemDTO> itens;


    public List<InventoryItemDTO> getAll() {
        return itens;
    }



}
