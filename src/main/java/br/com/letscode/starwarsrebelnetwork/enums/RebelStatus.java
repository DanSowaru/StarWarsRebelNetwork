package br.com.letscode.starwarsrebelnetwork.enums;

import lombok.Getter;

@Getter
public enum RebelStatus {
    TRAITOR("Traitor"),
    ALLY("Ally");

    private String status;

    RebelStatus(String status) {
        this.status = status;
    }
}
