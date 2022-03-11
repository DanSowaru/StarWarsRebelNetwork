package br.com.letscode.starwarsrebelnetwork.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnAlliesDTO {

    private String rebelsStatus;
    private int totalAlliesNumber;
    private String alliesPercentage;
    private List<ReturnRebelDTO> alliesList;
}
