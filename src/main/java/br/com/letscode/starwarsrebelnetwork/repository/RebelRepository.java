package br.com.letscode.starwarsrebelnetwork.repository;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import java.util.List;


@Repository
public class RebelRepository {

    private static List<RebelEntity> rebelList = new ArrayList();


    public RebelEntity saveRebelData(RebelEntity rebelEntity) {
        rebelEntity.setId(rebelList.get(rebelList.size()-1).getId()+1);
        rebelList.add(rebelEntity);
        return rebelEntity;
    }

    public static List<RebelEntity> getAll() {
        return rebelList;
    }

    public RebelEntity getRebel(String id) {
        return rebelList.stream().filter(rebel -> rebel.getId().equals(id)).findFirst().get();
    }
}


