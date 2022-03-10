package br.com.letscode.starwarsrebelnetwork.service;

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

    public ReturnTraitorsDTO getTraitorsReport() {
        ReturnTraitorsDTO traitorsDTO = new ReturnTraitorsDTO();
        DecimalFormat myFormatter = new DecimalFormat("##.#%");

        traitorsDTO.setTraitorsList(repository.getTraitorsList().stream().
                map((entidade -> rebelService.fromEntitytoReturnRebelDTO(entidade)))
                .collect(Collectors.toList()));

        traitorsDTO.setTotalTraitorsNumber(traitorsDTO.getTraitorsList().size());
        traitorsDTO.setTraitorsPercentage(myFormatter.format((double)traitorsDTO.getTotalTraitorsNumber() / repository.getAll().size()));

        return traitorsDTO;
    }
}
