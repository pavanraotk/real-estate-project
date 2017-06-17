package com.realty.project.service.implementation;

import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Unit;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.dao.repository.UnitRepository;
import com.realty.project.service.UnitService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.UnitInput;
import com.realty.project.service.output.UnitOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.realty.project.common.ErrorCodes.*;
import static com.realty.project.service.implementation.CommonService.*;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private BuildingRepopsitory buildingRepopsitory;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public UnitOutput addUnit(UnitInput unitInput) throws ServiceException {
        checkIfBuildingAndBlockExists(unitInput);
        Unit unit = getUnitFromUnitInput(unitInput);
        unitRepository.save(unit);
        return getUnitOutputFromUnitInput(unitInput);
    }

    @Override
    public UnitOutput updateUnit(UnitInput unitInput) throws ServiceException {
        checkIfBuildingAndBlockExists(unitInput);
        checkAndReturnIfUnitExists(unitInput.getId());
        Unit unit = getUnitFromUnitInput(unitInput);
        unitRepository.save(unit);
        return getUnitOutputFromUnitInput(unitInput);
    }

    @Override
    public UnitOutput getUnit(String unitId) throws ServiceException {
        Unit unit = checkAndReturnIfUnitExists(unitId);
        return getUnitOutputForUnit(unit);
    }

    @Override
    public UnitOutput getUnitForCustomerNumberBlockIdBuildingId(String customerNumber, String blockId, String buildingId) throws ServiceException {
        checkIfBuildingExists(buildingRepopsitory, buildingId);
        checkIfBlockExistsInBuilding(blockRepository, buildingId, blockId);
        Optional<Unit> unit = unitRepository.findByCustomerNumber(customerNumber);
        checkIfUnitBelongsToBuildingAndBlock(unit, buildingId, blockId);
        return getUnitOutputForUnit(unit.get());
    }

    @Override
    public void deleteUnit(String unitId) throws ServiceException {
        Unit unit = checkAndReturnIfUnitExists(unitId);
        Block block = getBlockForBlockId(blockRepository, unit.getBlockId());
        updateBlock(block, unit);
        unitRepository.delete(unit);
    }

    private void updateBlock(Block block, Unit unit) {
        switch (unit.getType()) {
            case ONE_BEDROOM_UNIT:
                block.setOneBedroomUnitsAvailable(block.getOneBedroomUnitsAvailable() + 1);
                blockRepository.save(block);
                break;
            case TWO_BEDROOM_UNIT:
                block.setTwoBedroomUnitsAvailable(block.getTwoBedroomUnitsAvailable() + 1);
                blockRepository.save(block);
                break;
            case THREE_BEDROOM_UNIT:
                block.setThreeBedroomUnitsAvailable(block.getThreeBedroomUnitsAvailable() + 1);
                blockRepository.save(block);
                break;
            case PENT_HOUSE:
                block.setPentHousesAvailable(block.getPentHousesAvailable() + 1);
                blockRepository.save(block);
                break;
        }
    }

    private void checkIfBuildingAndBlockExists(UnitInput unitInput) throws ServiceException {
        checkIfBuildingExists(buildingRepopsitory, unitInput.getBuildingId());
        checkIfBlockExistsInBuilding(blockRepository, unitInput.getBuildingId(), unitInput.getBlockId());
    }

    private void checkIfUnitBelongsToBuildingAndBlock(Optional<Unit> unit, String buildingId, String blockId) throws ServiceException {
        if (!unit.isPresent()) {
            throw new ServiceException(UNIT_DOES_NOT_EXIST);
        }
        if (!unit.get().getBuildingId().equalsIgnoreCase(buildingId)) {
            throw new ServiceException(UNIT_DOES_NOT_EXIST_IN_BUILDING);
        }
        if (!unit.get().getBlockId().equalsIgnoreCase(blockId)) {
            throw new ServiceException(UNIT_DOES_NOT_EXIST_IN_BLOCK);
        }
    }

    private Unit checkAndReturnIfUnitExists(String id) throws ServiceException {
        Optional<Unit> unit = unitRepository.findById(id);
        if (!unit.isPresent()) {
            throw new ServiceException(UNIT_DOES_NOT_EXIST);
        }
        return unit.get();
    }

    private Unit getUnitFromUnitInput(UnitInput unitInput) {
        Unit unit = new Unit();
        unit.setAddress(unitInput.getAddress());
        unit.setApartmentNumber(unitInput.getApartmentNumber());
        unit.setBuildingId(unitInput.getBuildingId());
        unit.setBlockId(unitInput.getBlockId());
        unit.setCustomerNumber(unitInput.getCustomerNumber());
        unit.setFloorNumber(unitInput.getFloorNumber());
        unit.setId(unitInput.getId());
        unit.setType(unitInput.getUnitType());
        return unit;
    }

    private UnitOutput getUnitOutputFromUnitInput(UnitInput unitInput) {
        UnitOutput unitOutput = new UnitOutput();
        unitOutput.setAddress(unitInput.getAddress());
        unitOutput.setApartmentNumber(unitInput.getApartmentNumber());
        unitOutput.setBuildingId(unitInput.getBuildingId());
        unitOutput.setBlockId(unitInput.getBlockId());
        unitOutput.setCustomerNumber(unitInput.getCustomerNumber());
        unitOutput.setFloorNumber(unitInput.getFloorNumber());
        unitOutput.setId(unitInput.getId());
        return unitOutput;
    }

    private UnitOutput getUnitOutputForUnit(Unit unit) {
        UnitOutput unitOutput = new UnitOutput();
        unitOutput.setAddress(unit.getAddress());
        unitOutput.setApartmentNumber(unit.getApartmentNumber());
        unitOutput.setBuildingId(unit.getBuildingId());
        unitOutput.setBlockId(unit.getBlockId());
        unitOutput.setCustomerNumber(unit.getCustomerNumber());
        unitOutput.setFloorNumber(unit.getFloorNumber());
        unitOutput.setId(unit.getId());
        unitOutput.setUnitType(unit.getType());
        return unitOutput;
    }

}
