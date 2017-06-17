package com.realty.project.dao.model;

import com.realty.project.common.UnitType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.realty.project.common.Constants.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = UNITS)
public class Unit {

    @Id
    @Column(name = ID)
    private String id;

    @Column(name = APARTMENT_NUMBER)
    private Integer apartmentNumber;

    @Column(name = FLOOR_NUMBER)
    private Integer floorNumber;

    @Column(name = BLOCK_ID)
    private String blockId;

    @Column(name = BUILDING_ID)
    private String buildingId;

    @Column(name = CUSTOMER_NUMBER)
    private String customerNumber;

    @Column(name = ADDRESS)
    private String address;
    
    @Column(name = TYPE)
    private UnitType type;
}
