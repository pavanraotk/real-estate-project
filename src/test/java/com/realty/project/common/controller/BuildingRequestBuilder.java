package com.realty.project.common.controller;

import com.realty.project.rest.request.BuildingRequest;

public class BuildingRequestBuilder {
    private String name;
    private String address;
    private Integer oneBedroomUnitsTotal;
    private Integer twoBedroomUnitsTotal;
    private Integer threeBedroomUnitsTotal;
    private Integer pentHousesTotal;
    private String blocks;
    private Integer oneBedroomUnitsAvailable;
    private Integer twoBedroomUnitsAvailable;
    private Integer threeBedroomUnitsAvailable;
    private Integer pentHousesAvailable;

    public BuildingRequestBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BuildingRequestBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BuildingRequestBuilder setOneBedroomUnitsTotal(Integer oneBedroomUnitsTotal) {
        this.oneBedroomUnitsTotal = oneBedroomUnitsTotal;
        return this;
    }

    public BuildingRequestBuilder setTwoBedroomUnitsTotal(Integer twoBedroomUnitsTotal) {
        this.twoBedroomUnitsTotal = twoBedroomUnitsTotal;
        return this;
    }

    public BuildingRequestBuilder setThreeBedroomUnitsTotal(Integer threeBedroomUnitsTotal) {
        this.threeBedroomUnitsTotal = threeBedroomUnitsTotal;
        return this;
    }

    public BuildingRequestBuilder setPentHousesTotal(Integer pentHousesTotal) {
        this.pentHousesTotal = pentHousesTotal;
        return this;
    }

    public BuildingRequestBuilder setBlocks(String blocks) {
        this.blocks = blocks;
        return this;
    }

    public BuildingRequestBuilder setOneBedroomUnitsAvailable(Integer oneBedroomUnitsAvailable) {
        this.oneBedroomUnitsAvailable = oneBedroomUnitsAvailable;
        return this;
    }

    public BuildingRequestBuilder setTwoBedroomUnitsAvailable(Integer twoBedroomUnitsAvailable) {
        this.twoBedroomUnitsAvailable = twoBedroomUnitsAvailable;
        return this;
    }

    public BuildingRequestBuilder setThreeBedroomUnitsAvailable(Integer threeBedroomUnitsAvailable) {
        this.threeBedroomUnitsAvailable = threeBedroomUnitsAvailable;
        return this;
    }

    public BuildingRequestBuilder setPentHousesAvailable(Integer pentHousesAvailable) {
        this.pentHousesAvailable = pentHousesAvailable;
        return this;
    }

    public BuildingRequestBuilder withData() {
        name = "Test Property";
        address = "Test Property, Test Main Road, Test City, 333";
        oneBedroomUnitsTotal = 100;
        twoBedroomUnitsTotal = 100;
        threeBedroomUnitsTotal = 100;
        pentHousesTotal = 100;
        blocks = "9";
        oneBedroomUnitsAvailable = 90;
        twoBedroomUnitsAvailable = 90;
        threeBedroomUnitsAvailable = 90;
        pentHousesAvailable = 90;
        return this;
    }

    public BuildingRequest build() {
        BuildingRequest building = new BuildingRequest(name, address, blocks, oneBedroomUnitsTotal, twoBedroomUnitsTotal, threeBedroomUnitsTotal,
                pentHousesTotal, oneBedroomUnitsAvailable, twoBedroomUnitsAvailable, threeBedroomUnitsAvailable, pentHousesAvailable);
        return building;
    }

}
