package br.com.letscode.starwarsrebelnetwork.repository;

import br.com.letscode.starwarsrebelnetwork.dto.LocalizationDTO;
import br.com.letscode.starwarsrebelnetwork.dto.RebelAccusationDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class RebelRepository {

    private static List<RebelEntity> rebelList = new ArrayList();
    private int id = 1;


    public RebelEntity saveRebelData(RebelEntity rebelEntity) {
        rebelEntity.setId(String.valueOf(id));
        rebelList.add(rebelEntity);
        id++;
        return rebelEntity;
    }

    public RebelEntity  updateRebel(RebelAccusationDTO rebelAccusationDTO) {
        rebelList.stream().filter(rebel -> rebel.getId().equals(rebelAccusationDTO.getId())).findFirst().get().setAccusations(rebelAccusationDTO.getAccusations());
        return null;
    }

    public RebelEntity updateRebel(LocalizationDTO localizationDTO) {
        rebelList.stream().filter(rebel -> rebel.getId().equals(localizationDTO.getId())).findFirst().get().setLocation(localizationDTO);
        return null;

    }

    public static List<RebelEntity> getAll() {
        return rebelList;
    }

    public RebelEntity getRebel(String id) {
        return rebelList.stream().filter(rebel -> rebel.getId().equals(id)).findFirst().get();
    }

    public List<RebelEntity> getTraitorsList() {
        return rebelList.stream()
                .filter(rebel -> rebel.getAccusations() > 2)
                .collect(Collectors.toList());
    }

    public List<RebelEntity> getAlliesList() {
        return rebelList.stream()
                .filter(rebel -> rebel.getAccusations() < 3)
                .collect(Collectors.toList());
    }

    public void updateRebelLocation(RebelEntity entity) {
        for (int i = 0; i < rebelList.size(); i++) {
            if (rebelList.get(i).getId().equals(entity.getId())) {
                rebelList.get(i).setLocation(entity.getLocation());
                return;
            }
        }
    }
}


