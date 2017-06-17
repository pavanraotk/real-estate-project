package com.realty.project.dao.model;

import lombok.*;

import javax.persistence.*;

import static com.realty.project.common.Constants.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name= BUILDINGS)
public class Building {

    @Id
    @Column(name = ID)
    private String id;

    @Column(name = NAME)
    private String name;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = ONE_BEDROOM_UNITS_TOTAL)
    private Integer oneBedroomUnitsTotal;

    @Column(name = TWO_BEDROOM_UNITS_TOTAL)
    private Integer twoBedroomUnitsTotal;

    @Column(name = THREE_BEDROOM_UNITS_TOTAL)
    private Integer threeBedroomUnitsTotal;

    @Column(name = PENT_HOUSES_TOTAL)
    private Integer pentHousesTotal;

    @Column(name = BLOCKS)
    private Integer blocks;

    @Column(name = ONE_BEDROOM_UNITS_AVAILABLE)
    private Integer oneBedroomUnitsAvailable;

    @Column(name = TWO_BEDROOM_UNITS_AVAILABLE)
    private Integer twoBedroomUnitsAvailable;

    @Column(name = THREE_BEDROOM_UNITS_AVAILABLE)
    private Integer threeBedroomUnitsAvailable;

    @Column(name = PENT_HOUSES_AVAILABLE)
    private Integer pentHousesAvailable;
}
