package br.com.letscode.starwarsrebelnetwork.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizationDTO {

    private String latitude;
    private String longitude;
    private String baseName;
    private String id;

    public LocalizationDTO(String latitude, String longitude, String baseName, String id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.baseName = baseName;
        this.id = id;
    }
}
