package br.com.letscode.starwarsrebelnetwork.repository;

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

    public static List<RebelEntity> getAll() {
        return rebelList;
    }

    public RebelEntity getRebel(String id) {
        return rebelList.stream().filter(rebel -> rebel.getId().equals(id)).findFirst().get();
    }

    public List<RebelEntity> getTraitorsList() {
        return rebelList.stream().filter(rebel -> rebel.getAccusations() > 2).collect(Collectors.toList());
    }
}


