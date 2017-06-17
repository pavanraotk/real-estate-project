package com.realty.project.common.dao;

import com.realty.project.dao.model.Block;

import static java.util.UUID.randomUUID;

public class BlockBuilder {

    private String id;
    private String blockId;
    private String name;
    private Integer oneBedroomUnitsTotal;
    private Integer twoBedroomUnitsTotal;
    private Integer threeBedroomUnitsTotal;
    private Integer pentHousesTotal;
    private Integer oneBedroomUnitsAvailable;
    private Integer twoBedroomUnitsAvailable;
    private Integer threeBedroomUnitsAvailable;
    private Integer pentHousesAvailable;
    private String buildingId;

    public BlockBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BlockBuilder setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public BlockBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BlockBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BlockBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BlockBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BlockBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BlockBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BlockBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BlockBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BlockBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BlockBuilder setBuildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public BlockBuilder withData(){
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

    public Block build(){
        Block block = new Block(id, blockId, name, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal, pentHousesTotal,
                oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable, buildingId);
        return block;
    }
}
