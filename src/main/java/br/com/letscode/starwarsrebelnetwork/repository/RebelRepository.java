package br.com.letscode.starwarsrebelnetwork.repository;

import br.com.letscode.starwarsrebelnetwork.dto.RebelDTO;
import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.letscode.starwarsrebelnetwork.repository.LocalizationRepository.registeredLocations;

@Repository
public class RebelRepository {

    private static List<RebelDTO> rebelList = new ArrayList();

    static {
        rebelList.addAll(Arrays.asList(new RebelDTO("Luke", "53", Gender.MALE, registeredLocations.get(0), null, null),
                                    new RebelDTO("Han Solo", "60", Gender.MALE, registeredLocations.get(1), null, null),
                                    new RebelDTO("Leia", "45", Gender.FEMALE, registeredLocations.get(2), null, null),
                                    new RebelDTO("Chewbacca", "100", Gender.MALE, registeredLocations.get(3), null, null)));
    }


    public static List<RebelDTO> getAll() {
        return rebelList;
    }
}
