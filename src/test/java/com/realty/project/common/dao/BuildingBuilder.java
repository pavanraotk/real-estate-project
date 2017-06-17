package com.realty.project.common.dao;

import com.realty.project.dao.model.Building;

import static java.util.UUID.randomUUID;

public class BuildingBuilder {

    private String id;
    private String name;
    private String address;
    private Integer oneBedroomUnitsTotal;
    private Integer twoBedroomUnitsTotal;
    private Integer threeBedroomUnitsTotal;
    private Integer pentHousesTotal;
    private Integer blocks;
    private Integer oneBedroomUnitsAvailable;
    private Integer twoBedroomUnitsAvailable;
    private Integer threeBedroomUnitsAvailable;
    private Integer pentHousesAvailable;

    public BuildingBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BuildingBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BuildingBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BuildingBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BuildingBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BuildingBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BuildingBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BuildingBuilder setBlocks(Integer blocks) {
        this.blocks = blocks;
        return this;
    }

    public BuildingBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BuildingBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BuildingBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BuildingBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BuildingBuilder withData() {
        id = randomUUID().toString();
        name = "Test Property";
        address = "Test Property, Test Main Road, Test City, 333";
        oneBedroomUnitsTotal = 100;
        twoBedroomUnitsTotal = 100;
        threeBedroomUnitsTotal = 100;
        pentHousesTotal = 100;
        blocks = 10;
        oneBedroomUnitsAvailable = 90;
        twoBedroomUnitsAvailable = 90;
        threeBedroomUnitsAvailable = 90;
        pentHousesAvailable = 90;
        return this;
    }

    public Building build() {
        Building building = new Building(id, name, address, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal,
                pentHousesTotal, blocks, oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable);
        return building;
    }
}