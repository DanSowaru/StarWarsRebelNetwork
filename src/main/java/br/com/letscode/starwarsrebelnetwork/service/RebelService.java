package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReturnRebelDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RebelService {
    private final RebelRepository repository;

    public List<ReturnRebelDTO> listAllRebels() {
        List<ReturnRebelDTO> returnList = repository.getAll().stream()
                .map((entidade -> fromEntitytoReturnRebelDTO(entidade)))
                .collect(Collectors.toList());

        return returnList;
    }


    public void newRebel(RebelDTO rebelDTO) {
        RebelEntity entity = new RebelEntity();

        entity.setName(rebelDTO.getName());
        entity.setAge(rebelDTO.getAge());
        entity.setGender(rebelDTO.getGender());
        entity.setBaseName(rebelDTO.getLocation().getBaseName());
        entity.setInventory(rebelDTO.getInventory());
        entity.setAccusations(rebelDTO.getAccusations());

        RebelEntity rebelEntityDataSaved = repository.saveRebelData(entity);

        //TODO: FAZER O RETORNO DO REBEL SALVO;
    }

    public ReturnRebelDTO fromEntitytoReturnRebelDTO(RebelEntity entity) {
        ReturnRebelDTO rebelDTO = new ReturnRebelDTO();
        rebelDTO.setName(entity.getName());
        rebelDTO.setAge(entity.getAge());
        rebelDTO.setGender(entity.getGender());
        rebelDTO.setBaseName(entity.getBaseName());
        rebelDTO.setInventory(entity.getInventory());
        rebelDTO.setAccusations(entity.getAccusations());

        return rebelDTO;
    }

}
