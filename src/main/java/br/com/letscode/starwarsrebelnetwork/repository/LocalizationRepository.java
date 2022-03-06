package br.com.letscode.starwarsrebelnetwork.repository;

import br.com.letscode.starwarsrebelnetwork.model.Location;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class LocalizationRepository {

    private static List<Location> registeredLocations = new ArrayList<>();

    static {
        registeredLocations.addAll(
                Arrays.asList(
                        new Location("Crait", "110", "525"),
                        new Location("Dantooine", "142", "378"),
                        new Location("Hope Station", "501", "405"),
                        new Location("Hoth", "163", "549"),
                        new Location("Yavin", "481", "102")
                )
        );
    }
}



