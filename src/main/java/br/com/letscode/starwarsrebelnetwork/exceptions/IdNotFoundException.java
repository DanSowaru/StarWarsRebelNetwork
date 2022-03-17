package br.com.letscode.starwarsrebelnetwork.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdNotFoundException extends RuntimeException{
    private String returnMessage;

    public IdNotFoundException(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
