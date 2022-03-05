package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;

public class RebelDTO {

    private String name;
    private String age;
    private Gender gender;
    private LocalizationDTO localization;
    private InventoryDTO inventory;
    private Short accusations;
}
