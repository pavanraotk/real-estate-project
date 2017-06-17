package com.realty.project.common.service;

import com.realty.project.common.UnitType;
import com.realty.project.service.input.UnitInput;

import static com.realty.project.common.UnitType.THREE_BEDROOM_UNIT;
import static java.util.UUID.randomUUID;

public class UnitInputBuilder {

    private String id;
    private Integer apartmentNumber;
    private Integer floorNumber;
    private String blockId;
    private String buildingId;
    private String customerNumber;
    private String address;
    private UnitType type;

    public UnitInputBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UnitInputBuilder setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public UnitInputBuilder setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
        return this;
    }

    public UnitInputBuilder setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public UnitInputBuilder setBuildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public UnitInputBuilder setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    public UnitInputBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UnitInputBuilder setType(UnitType type) {
        this.type = type;
        return this;
    }

    public UnitInputBuilder withData() {
        id = randomUUID().toString();
        apartmentNumber = 201;
        floorNumber = 2;
        blockId = randomUUID().toString();
        buildingId = randomUUID().toString();
        customerNumber = "+919663370912";
        address = "Flat A-209, Sruthika Springfields, Singapura Main Road, Vidyaranyapura, Bengaluru 560097";
        type = THREE_BEDROOM_UNIT;
        return this;
    }

    public UnitInput build() {
        return new UnitInput(id, apartmentNumber, floorNumber, blockId, buildingId, customerNumber, address, type);
    }

}
