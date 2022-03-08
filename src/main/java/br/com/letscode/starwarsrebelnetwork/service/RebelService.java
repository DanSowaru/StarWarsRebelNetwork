package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RebelService {
    private final RebelRepository repository;

    public List<RebelEntity> listAllRebels() {
        return repository.getAll();
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

}
