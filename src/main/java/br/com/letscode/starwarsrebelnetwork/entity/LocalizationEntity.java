package br.com.letscode.starwarsrebelnetwork.entity;

public class LocalizationEntity {

        private String latitude;
        private String longitude;
        private String baseName;
        private String id;

    public LocalizationEntity(String latitude, String longitude, String baseName, String id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.baseName = baseName;
        this.id = id;
    }
}
