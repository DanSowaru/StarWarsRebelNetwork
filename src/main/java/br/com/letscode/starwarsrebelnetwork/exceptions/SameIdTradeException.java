package br.com.letscode.starwarsrebelnetwork.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SameIdTradeException extends RuntimeException {
    private String returnMessage;

    public SameIdTradeException(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
