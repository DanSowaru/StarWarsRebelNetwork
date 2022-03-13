package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.RebelAccusationDTO;
import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReturnRebelDTO;
import br.com.letscode.starwarsrebelnetwork.dto.request.RebelPatchLocationRequestDTO;
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
    private final InventoryService inventoryService;

    public List<ReturnRebelDTO> listAllRebels() {

        return repository.getAll().stream()
                .map((this::mapEntityToReturnRebelDTO))
                .collect(Collectors.toList());

    }


    public void newRebel(RebelDTO rebelDTO) {
        RebelEntity entity = new RebelEntity();

        entity.setName(rebelDTO.getName());
        entity.setAge(rebelDTO.getAge());
        entity.setGender(rebelDTO.getGender());
        entity.setLocation(rebelDTO.getLocation());
        entity.setInventory(inventoryService.inventoryDTOToInventoryEntity(rebelDTO.getInventory()));
        entity.setAccusations(rebelDTO.getAccusations());

        RebelEntity rebelEntityDataSaved = repository.saveRebelData(entity);

        //TODO: FAZER O RETORNO DO REBEL SALVO;
    }

    public ReturnRebelDTO mapEntityToReturnRebelDTO(RebelEntity entity) {
        ReturnRebelDTO rebelDTO = new ReturnRebelDTO();
        rebelDTO.setId(entity.getId());
        rebelDTO.setName(entity.getName());
        rebelDTO.setAge(entity.getAge());
        rebelDTO.setGender(entity.getGender());
        rebelDTO.setLocation(entity.getLocation());
        rebelDTO.setInventory(inventoryService.mapInventoryEntityToInventoryDTO(entity.getInventory()));
        rebelDTO.setAccusations(entity.getAccusations());

        return rebelDTO;
    }

    public RebelEntity fromRebelDtoToRebelEntity(RebelDTO rebelDTO) {
        RebelEntity entity = new RebelEntity();

        entity.setName(rebelDTO.getName());
        entity.setAge(rebelDTO.getAge());
        entity.setGender(rebelDTO.getGender());
        entity.setLocation(rebelDTO.getLocation());
        entity.setInventory(inventoryService.inventoryDTOToInventoryEntity(rebelDTO.getInventory()));
        entity.setAccusations(rebelDTO.getAccusations());

        return entity;
    }

    public ReturnRebelDTO getRebelById(String rebelId) {
        RebelEntity rebelEntity = repository.getRebel(rebelId);
        return mapEntityToReturnRebelDTO(rebelEntity);
    }

    public RebelAccusationDTO accuseRebel(String rebelId) {
        RebelEntity rebelEntity = repository.getRebel(rebelId);
        RebelAccusationDTO rebelAccusationDTO = mapEntityToAcusationDTO(rebelId);
        rebelAccusationDTO.setAccusations((short) (rebelAccusationDTO.getAccusations() + 1));
        repository.updateRebel(rebelAccusationDTO);
        return rebelAccusationDTO;
    }

    public RebelAccusationDTO mapEntityToAcusationDTO(String rebelId) {
        RebelEntity rebelEntity = repository.getRebel(rebelId);
        RebelAccusationDTO rebelAccusationDTO = new RebelAccusationDTO(rebelEntity.getAccusations(), rebelId);
        return rebelAccusationDTO;
    }

    public ReturnRebelDTO update(String id, RebelPatchLocationRequestDTO rebelPatchLocationRequestDTO) {
        RebelEntity entity = repository.getRebel(id);
        entity.setLocation(rebelPatchLocationRequestDTO.getLocation());

        repository.updateRebelLocation(entity);
        return mapEntityToReturnRebelDTO(repository.getRebel(id));
    }

}
