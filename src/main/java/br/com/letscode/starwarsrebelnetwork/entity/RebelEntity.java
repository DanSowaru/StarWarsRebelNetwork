package br.com.letscode.starwarsrebelnetwork.entity;


import br.com.letscode.starwarsrebelnetwork.dto.InventoryDTO;
import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.Data;

@Data
public class RebelEntity {


    private String name;
    private String age;
    private Gender gender;
    private String baseName;
    private InventoryDTO inventory;
    private Short accusations;
    private String id;

    public RebelEntity(String name, String age, Gender gender, String baseName, InventoryDTO inventory, Short accusations, String id) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.baseName = baseName;
        this.inventory = inventory;
        this.accusations = accusations;
        this.id = id;
    }

    public RebelEntity() {

    }
}
