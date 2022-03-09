package br.com.letscode.starwarsrebelnetwork.dto;

import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RebelAccusationDTO {

   private Short accusations;
   private String id;
}
