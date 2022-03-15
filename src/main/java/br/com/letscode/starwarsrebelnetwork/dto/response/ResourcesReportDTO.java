package br.com.letscode.starwarsrebelnetwork.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcesReportDTO {
    private double weaponAverage;
    private double ammoAverage;
    private double waterAverage;
    private double foodAverage;
    private int lostResources;
}
