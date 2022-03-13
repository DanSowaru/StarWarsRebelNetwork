package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.ReportSummaryDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.enums.RebelStatus;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RebelReportService {
    private final RebelRepository repository;
    private final RebelService rebelService;
    private final DecimalFormat myFormatter = new DecimalFormat("##.#%");

    public ReportSummaryDTO getSummaryReport(RebelStatus status) {
        ReportSummaryDTO summaryDTO = new ReportSummaryDTO();

        summaryDTO.setRebelList(getRebelList(status).stream()
                .map((rebelService::mapEntityToReturnRebelDTO))
                .collect(Collectors.toList()));

        summaryDTO.setTotal(summaryDTO.getRebelList().size());
        summaryDTO.setPercentage(myFormatter.format((double) summaryDTO.getTotal() / repository.getAll().size()));
        summaryDTO.setStatus(status);

        return summaryDTO;
    }

    private List<RebelEntity> getRebelList(RebelStatus status) {
        return (status == RebelStatus.ALLY) ? repository.getAlliesList() : repository.getTraitorsList();
    }
}
