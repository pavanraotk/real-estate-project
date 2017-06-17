package com.realty.project.common.dao;

import com.realty.project.common.UnitType;
import com.realty.project.dao.model.Unit;

import static com.realty.project.common.UnitType.THREE_BEDROOM_UNIT;
import static java.util.UUID.randomUUID;

public class UnitBuilder {

    private String id;
    private Integer apartmentNumber;
    private Integer floorNumber;
    private String blockId;
    private String buildingId;
    private String customerNumber;
    private String address;
    private UnitType type;

    public UnitBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UnitBuilder setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public UnitBuilder setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
        return this;
    }

    public UnitBuilder setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public UnitBuilder setBuildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public UnitBuilder setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    public UnitBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UnitBuilder setType(UnitType type) {
        this.type = type;
        return this;
    }

    public UnitBuilder withData(){
        id = randomUUID().toString();
        apartmentNumber = 201;
        floorNumber = 2;
        blockId = randomUUID().toString();
        buildingId = randomUUID().toString();
        customerNumber = "+919663370912";
        address = "Flat A-209, Sruthika Springfields, Singapura Main Road, Vidyaranyapura, Bengaluru 560097";
        type  = THREE_BEDROOM_UNIT;
        return this;
    }

    public Unit build(){
        return new Unit(id, apartmentNumber, floorNumber, blockId, buildingId, customerNumber, address, type);
    }
}



