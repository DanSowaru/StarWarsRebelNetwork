package br.com.letscode.starwarsrebelnetwork.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotEqualTradeSumException extends RuntimeException{
    private String returnMessage;
    private int firstRebelOffer;
    private int secondRebelOffer;

    public NotEqualTradeSumException(String returnMessage, int firstRebelOffer, int secondRebelOffer) {
        this.returnMessage = returnMessage;
        this.firstRebelOffer = firstRebelOffer;
        this.secondRebelOffer = secondRebelOffer;
    }

    public String buildMessage() {
        String completeMessage = returnMessage + "\n" + "First Rebel Offer: " + firstRebelOffer + "\n" + "First Rebel Offer: " + secondRebelOffer;
        return completeMessage;
    }
}
