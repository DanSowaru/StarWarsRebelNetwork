package br.com.letscode.starwarsrebelnetwork.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraitorTradeException extends RuntimeException{
    private String returnMessage;
    private String firstId;
    private short firstAccusations;
    private String secondId;
    private short secondAccusations;


    public TraitorTradeException(String returnMessage, String firstId, String secondId, short firstAccusations, short secondAccusations) {
        this.returnMessage = returnMessage;
        this.firstId = firstId;
        this.secondId = secondId;
        this.firstAccusations = firstAccusations;
        this.secondAccusations = secondAccusations;
    }

    public String buildMessage() {
        String completeMessage = returnMessage + "\n" + "Rebel id " + firstId + ": number of accusations " + firstAccusations + "\n" + "Rebel id " + secondId + ": number of accusations "+ secondAccusations;
        return completeMessage;
    }
}
