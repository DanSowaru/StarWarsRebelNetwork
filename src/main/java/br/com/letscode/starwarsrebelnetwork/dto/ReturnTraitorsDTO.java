package br.com.letscode.starwarsrebelnetwork.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnTraitorsDTO {

    private int totalTraitorsNumber;
    private String traitorsPercentage;
    private List<ReturnRebelDTO> traitorsList;

}
