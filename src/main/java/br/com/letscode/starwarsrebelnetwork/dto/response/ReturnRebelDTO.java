package br.com.letscode.starwarsrebelnetwork.dto.response;

import br.com.letscode.starwarsrebelnetwork.dto.request.InventoryDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.LocalizationDTO;
import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnRebelDTO {

    private String id;
    private String name;
    private String age;
    private Gender gender;
    private LocalizationDTO location;
    private InventoryDTO inventory;
    private Short accusations;


}
