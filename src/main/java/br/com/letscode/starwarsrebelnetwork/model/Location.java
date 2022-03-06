package br.com.letscode.starwarsrebelnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

    private String baseName;
    private String lattitude;
    private String longitude;
}
