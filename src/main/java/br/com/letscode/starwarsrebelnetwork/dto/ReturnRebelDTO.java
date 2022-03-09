package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnRebelDTO {

    private String name;
    private String age;
    private Gender gender;
    private String baseName;
    private InventoryDTO inventory;
    private Short accusations;

}
