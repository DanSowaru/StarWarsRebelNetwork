package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.ReturnAlliesDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReturnTraitorsDTO;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RebelReportService {
    private final RebelRepository repository;
    private final RebelService rebelService;
    private final DecimalFormat myFormatter = new DecimalFormat("##.#%");

    public ReturnTraitorsDTO getTraitorsReport() {
        ReturnTraitorsDTO traitorsDTO = new ReturnTraitorsDTO();

        traitorsDTO.setTraitorsList(repository.getTraitorsList().stream()
                .map((entity -> rebelService.fromEntitytoReturnRebelDTO(entity)))
                .collect(Collectors.toList()));

        traitorsDTO.setTotalTraitorsNumber(traitorsDTO.getTraitorsList().size());
        traitorsDTO.setTraitorsPercentage(myFormatter.format((double)traitorsDTO.getTotalTraitorsNumber() / repository.getAll().size()));
        traitorsDTO.setRebelsStatus("Traitors");

        return traitorsDTO;
    }

    public ReturnAlliesDTO getAlliesReport() {
        ReturnAlliesDTO alliesDTO = new ReturnAlliesDTO();

        alliesDTO.setAlliesList(repository.getAlliesList().stream()
                .map(entity -> rebelService.fromEntitytoReturnRebelDTO(entity))
                .collect(Collectors.toList()));

        alliesDTO.setTotalAlliesNumber(alliesDTO.getAlliesList().size());
        alliesDTO.setAlliesPercentage(myFormatter.format((double) alliesDTO.getTotalAlliesNumber() / repository.getAll().size()));
        alliesDTO.setRebelsStatus("Allies");

        return alliesDTO;
    }
}
