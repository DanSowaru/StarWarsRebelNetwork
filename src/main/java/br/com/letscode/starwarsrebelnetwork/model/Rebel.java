package br.com.letscode.starwarsrebelnetwork.model;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rebel {

    private String name;
    private String age;
    private Gender gender;
    private Location location;
    private Inventory inventory;
    private Short accusations = 0;
}
