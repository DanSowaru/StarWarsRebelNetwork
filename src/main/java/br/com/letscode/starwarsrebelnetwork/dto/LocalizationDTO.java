package br.com.letscode.starwarsrebelnetwork.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizationDTO {

    private String latitude;
    private String longitude;
    private String baseName;

    public LocalizationDTO(String latitude, String longitude, String baseName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.baseName = baseName;
    }
}
