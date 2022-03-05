package br.com.letscode.starwarsrebelnetwork.model;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.Data;

@Data
public class Rebel {

    private String name;
    private String age;
    private Gender gender;
    private Localization localization;
    private Inventory inventory;
    private Short accusations;
}
