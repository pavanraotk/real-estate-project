package com.realty.project.service.input;

import com.realty.project.common.UnitType;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UnitInput {
    private String id;
    private Integer apartmentNumber;
    private Integer floorNumber;
    private String blockId;
    private String buildingId;
    private String customerNumber;
    private String address;
    private UnitType unitType;
}
