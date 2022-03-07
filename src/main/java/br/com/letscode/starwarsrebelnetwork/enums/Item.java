package br.com.letscode.starwarsrebelnetwork.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Item {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    private final Integer price;

}




