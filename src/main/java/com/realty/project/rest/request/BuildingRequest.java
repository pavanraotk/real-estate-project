package com.realty.project.rest.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.realty.project.common.Constants.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BuildingRequest {

    @NotNull
    @Length(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    private String name;

    @NotNull
    @Length(min = MIN_ADDRESS_LENGTH, max = MAX_ADDRESS_LENGTH)
    private String address;

    @Pattern(regexp = BLOCKS_REGULAR_EXPRESSION)
    private String blocks;

    private Integer oneBedroomUnitsTotal;

    private Integer twoBedroomUnitsTotal;

    private Integer threeBedroomUnitsTotal;

    private Integer pentHousesTotal;

    private Integer oneBedroomUnitsAvailable;

    private Integer twoBedroomUnitsAvailable;

    private Integer threeBedroomUnitsAvailable;

    private Integer pentHousesAvailable;

}
