package br.com.letscode.starwarsrebelnetwork.entity;



import br.com.letscode.starwarsrebelnetwork.dto.LocalizationDTO;
import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.Data;

@Data
public class RebelEntity {


    private String id;
    private String name;
    private String age;
    private Gender gender;
    private LocalizationDTO location;
    private InventoryEntity inventory;
    private Short accusations;


    public RebelEntity(String name, String age, Gender gender, LocalizationDTO location, InventoryEntity inventory, Short accusations, String id) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.inventory = inventory;
        this.accusations = accusations;
        this.id = id;
    }

    public RebelEntity() {

    }
}
