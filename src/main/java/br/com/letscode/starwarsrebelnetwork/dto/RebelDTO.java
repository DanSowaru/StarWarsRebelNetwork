package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import br.com.letscode.starwarsrebelnetwork.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RebelDTO {

    private String name;
    private String age;
    private Gender gender;
    private Location location;
    private InventoryDTO inventory;
    private Short accusations;
}
