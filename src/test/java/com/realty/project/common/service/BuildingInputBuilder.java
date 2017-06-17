package com.realty.project.common.service;

import com.realty.project.service.input.BlockInput;
import com.realty.project.service.input.BuildingInput;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class BuildingInputBuilder {
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
    private Integer fourBedroomUnitsAvailable;
    private Integer pentHousesAvailable;
    private List<BlockInput> blockInputList;

    public BuildingInputBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BuildingInputBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BuildingInputBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BuildingInputBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BuildingInputBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BuildingInputBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BuildingInputBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BuildingInputBuilder setBlocks(Integer blocks) {
        this.blocks = blocks;
        return this;
    }

    public BuildingInputBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BuildingInputBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BuildingInputBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BuildingInputBuilder setFourBedroomUnitsAvailable(Integer fourBedroomUnitsAvailable) {
        this.fourBedroomUnitsAvailable = fourBedroomUnitsAvailable;
        return this;
    }

    public BuildingInputBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BuildingInputBuilder setBlockInputList(List<BlockInput> blockInputList) {
        this.blockInputList = blockInputList;
        return this;
    }

    public BuildingInputBuilder withData() {
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
        blockInputList = generateBlockInputList();
        return this;
    }

    private List<BlockInput> generateBlockInputList() {
        List<BlockInput> blockInputs = new ArrayList<>();
        blockInputs.add(new BlockInputBuilder().withData().build());
        return blockInputs;
    }

    public BuildingInput build() {
        BuildingInput buildingInput = new BuildingInput(id, name, address, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal, pentHousesTotal, blocks, oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable, blockInputList);
        return buildingInput;
    }
}
