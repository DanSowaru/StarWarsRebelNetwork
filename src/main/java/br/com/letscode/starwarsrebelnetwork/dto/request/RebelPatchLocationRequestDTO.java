package br.com.letscode.starwarsrebelnetwork.dto.request;

import br.com.letscode.starwarsrebelnetwork.dto.LocalizationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelPatchLocationRequestDTO {

    private LocalizationDTO location;
}
