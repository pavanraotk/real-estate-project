package com.realty.project.common.service;

import com.realty.project.service.input.BlockInput;

import static java.util.UUID.randomUUID;

public class BlockInputBuilder {

    private String id;
    private String blockId;
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

    public BlockInputBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BlockInputBuilder setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public BlockInputBuilder setBuildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public BlockInputBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BlockInputBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BlockInputBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BlockInputBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BlockInputBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BlockInputBuilder setBlocks(Integer blocks) {
        this.blocks = blocks;
        return this;
    }

    public BlockInputBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BlockInputBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BlockInputBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BlockInputBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BlockInputBuilder withData() {
        id = randomUUID().toString();
        blockId = "A";
        name = "Aster";
        oneBedroomUnitsTotal = 100;
        twoBedroomUnitsTotal = 100;
        threeBedroomUnitsTotal = 100;
        pentHousesTotal = 100;
        oneBedroomUnitsAvailable = 100;
        twoBedroomUnitsAvailable = 100;
        threeBedroomUnitsAvailable = 100;
        pentHousesAvailable = 100;
        buildingId = randomUUID().toString();
        return this;
    }

    public BlockInput build() {
        return new BlockInput(id, blockId, buildingId, name, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal, pentHousesTotal, oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable);
    }
}
