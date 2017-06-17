package com.realty.project.service.implementation;

import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.service.BlockService;
import com.realty.project.service.BuildingService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import com.realty.project.service.input.BuildingInput;
import com.realty.project.service.output.BuildingOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.realty.project.common.ErrorCodes.BUILDING_ALREADY_EXISTS;
import static com.realty.project.common.ErrorCodes.BUILDING_DOES_NOT_EXIST;
import static java.util.UUID.randomUUID;

@Service
public class BuildingServiceImpl implements BuildingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingServiceImpl.class);

    @Autowired
    private BlockService blockService;

    @Autowired
    private BuildingRepopsitory buildingRepopsitory;

    @Override
    public BuildingOutput addBuilding(BuildingInput buildingInput) throws ServiceException {
        final Optional<Building> existingBuilding = buildingRepopsitory.findByName(buildingInput.getName());
        if (existingBuilding.isPresent()) {
            LOGGER.error("Error in adding building, building already exists");
            throw new ServiceException(BUILDING_ALREADY_EXISTS);
        }
        Building building = generateBuilding(buildingInput);
        buildingRepopsitory.save(building);
        if (buildingInput.getBlockInputList() != null && !buildingInput.getBlockInputList().isEmpty()) {
            addBlocks(buildingInput.getBlockInputList(), building.getId());
        }
        return generateBuildingOutput(buildingInput);
    }

    @Override
    public BuildingOutput updateBuilding(BuildingInput buildingInput) throws ServiceException {
        final Optional<Building> building = buildingRepopsitory.findByName(buildingInput.getName());
        if (!building.isPresent()) {
            LOGGER.error("Error in updating building, building does not exist");
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
        Building updatedBuilding = buildingRepopsitory.save(generateBuilding(buildingInput));
        return generateBuildingOutput(updatedBuilding);
    }

    @Override
    public BuildingOutput getBuildingByName(String buildingName) throws ServiceException {
        final Optional<Building> building = buildingRepopsitory.findByName(buildingName);
        if (!building.isPresent()) {
            LOGGER.error("Error in getting building, building does not exist");
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
        return generateBuildingOutput(building.get());
    }

    @Override
    public void deleteBuilding(String buildingId) throws ServiceException {
        Optional<Building> building = buildingRepopsitory.findById(buildingId);
        if (!building.isPresent()) {
            LOGGER.error("Error in deleting building, building does not exist");
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
        buildingRepopsitory.delete(building.get());
    }

    private BuildingOutput generateBuildingOutput(Building building) {
        BuildingOutput buildingOutput = new BuildingOutput();
        buildingOutput.setId(building.getId());
        buildingOutput.setName(building.getName());
        buildingOutput.setAddress(building.getAddress());
        buildingOutput.setBlocks(building.getBlocks());
        buildingOutput.setOneBedroomUnitsAvailable(building.getOneBedroomUnitsTotal());
        buildingOutput.setTwoBedroomUnitsAvailable(building.getTwoBedroomUnitsTotal());
        buildingOutput.setThreeBedroomUnitsAvailable(building.getThreeBedroomUnitsTotal());
        buildingOutput.setPentHousesAvailable(building.getPentHousesTotal());
        buildingOutput.setOneBedroomUnitsAvailable(building.getOneBedroomUnitsAvailable());
        buildingOutput.setTwoBedroomUnitsAvailable(building.getTwoBedroomUnitsAvailable());
        buildingOutput.setThreeBedroomUnitsAvailable(building.getThreeBedroomUnitsAvailable());
        buildingOutput.setPentHousesAvailable(building.getPentHousesAvailable());
        return buildingOutput;
    }

    private BuildingOutput generateBuildingOutput(BuildingInput buildingInput) {
        BuildingOutput buildingOutput = new BuildingOutput();
        buildingOutput.setId(buildingInput.getId());
        buildingOutput.setAddress(buildingInput.getAddress());
        buildingOutput.setName(buildingInput.getName());
        buildingOutput.setOneBedroomUnitsAvailable(buildingInput.getOneBedroomUnitsAvailable());
        buildingOutput.setTwoBedroomUnitsAvailable(buildingInput.getTwoBedroomUnitsAvailable());
        buildingOutput.setThreeBedroomUnitsAvailable(buildingInput.getThreeBedroomUnitsAvailable());
        buildingOutput.setPentHousesAvailable(buildingInput.getPentHousesAvailable());
        buildingOutput.setOneBedroomUnitsTotal(buildingInput.getOneBedroomUnitsTotal());
        buildingOutput.setTwoBedroomUnitsTotal(buildingInput.getTwoBedroomUnitsTotal());
        buildingOutput.setThreeBedroomUnitsTotal(buildingInput.getThreeBedroomUnitsTotal());
        buildingOutput.setPentHousesTotal(buildingInput.getPentHousesTotal());
        return buildingOutput;
    }

    private void addBlocks(List<BlockInput> blockInputs, String id) throws ServiceException {
        for (BlockInput blockInput : blockInputs) {
            blockInput.setBuildingId(id);
            blockService.addBlock(blockInput);
        }
    }

    private Building generateBuilding(BuildingInput buildingInput) {
        Building building = new Building();
        building.setId(buildingInput.getId());
        building.setName(buildingInput.getName());
        building.setAddress(buildingInput.getAddress());
        building.setBlocks(buildingInput.getBlocks());
        building.setOneBedroomUnitsAvailable(buildingInput.getOneBedroomUnitsAvailable());
        building.setTwoBedroomUnitsAvailable(buildingInput.getTwoBedroomUnitsAvailable());
        building.setThreeBedroomUnitsAvailable(buildingInput.getThreeBedroomUnitsAvailable());
        building.setPentHousesAvailable(buildingInput.getPentHousesAvailable());
        building.setOneBedroomUnitsTotal(buildingInput.getOneBedroomUnitsTotal());
        building.setTwoBedroomUnitsTotal(buildingInput.getTwoBedroomUnitsTotal());
        building.setThreeBedroomUnitsTotal(buildingInput.getThreeBedroomUnitsTotal());
        building.setPentHousesTotal(buildingInput.getPentHousesTotal());
        return building;
    }

}
