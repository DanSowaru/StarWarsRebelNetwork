package br.com.letscode.starwarsrebelnetwork.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnTraitorsDTO {

    private String rebelsStatus;
    private int totalTraitorsNumber;
    private String traitorsPercentage;
    private List<ReturnRebelDTO> traitorsList;

}
