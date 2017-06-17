package com.realty.project.common.service;

import com.realty.project.service.output.BlockOutput;
import com.realty.project.service.output.BuildingOutput;

import java.util.List;

import static java.util.UUID.randomUUID;

public class BuildingOutputBuilder {

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
    private List<BlockOutput> blockOutputList;

    public BuildingOutputBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BuildingOutputBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BuildingOutputBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BuildingOutputBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BuildingOutputBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BuildingOutputBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BuildingOutputBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BuildingOutputBuilder setBlocks(Integer blocks) {
        this.blocks = blocks;
        return this;
    }

    public BuildingOutputBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BuildingOutputBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BuildingOutputBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BuildingOutputBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BuildingOutputBuilder setBlockOutputList(List<BlockOutput> blockOutputList) {
        this.blockOutputList = blockOutputList;
        return this;
    }

    public BuildingOutputBuilder withData() {
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

    public BuildingOutput build(){
        BuildingOutput buildingOutput = new BuildingOutput(id, name, address, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal, pentHousesTotal, blocks, oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable, blockOutputList);
        return buildingOutput;
    }
}
