package br.com.letscode.starwarsrebelnetwork.dto.response;

import br.com.letscode.starwarsrebelnetwork.enums.RebelStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportSummaryDTO {

    private RebelStatus status;
    private int total;
    private String percentage;
    private List<ReturnRebelDTO> rebelList;
}
