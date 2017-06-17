package com.realty.project.rest.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BuildingResponse {

    private String id;
    private String name;
    private String address;
    private Integer blocks;
    private Integer oneBedroomUnitsTotal;
    private Integer twoBedroomUnitsTotal;
    private Integer threeBedroomUnitsTotal;
    private Integer pentHousesTotal;
    private Integer oneBedroomUnitsAvailable;
    private Integer twoBedroomUnitsAvailable;
    private Integer threeBedroomUnitsAvailable;
    private Integer pentHousesAvailable;
}
