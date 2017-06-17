package com.realty.project.service.output;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BlockOutput {
    private String id;
    private String block;
    private String buildingId;
    private String name;
    private Integer oneBedroomUnitsTotal;
    private Integer twoBedroomUnitsTotal;
    private Integer threeBedroomUnitsTotal;
    private Integer pentHousesTotal;
    private Integer blocks;
    private Integer oneBedroomUnitsAvailable;
    private Integer twoBedroomUnitsAvailable;
    private Integer threeBedroomUnitsAvailable;
    private Integer pentHousesAvailable;
}
