package com.realty.project.service.implementation;

import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static com.realty.project.common.ErrorCodes.*;

public final class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceImpl.class);

    public static void checkIfBuildingExists(BuildingRepopsitory buildingRepopsitory, String buildingId) throws ServiceException {
        if (!buildingRepopsitory.findById(buildingId).isPresent()) {
            LOGGER.error("Building does not exist of {}", buildingId);
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
    }

    public static Building getBuilding(BuildingRepopsitory buildingRepopsitory, String buildingName) throws ServiceException {
        Optional<Building> building = buildingRepopsitory.findByName(buildingName);
        if (!building.isPresent()) {
            LOGGER.error("Building does not exist for given name {}", buildingName);
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
        return building.get();
    }

    public static Building getBuildingForBuildingId(BuildingRepopsitory buildingRepopsitory, String buildingId) throws ServiceException {
        Optional<Building> building = buildingRepopsitory.findById(buildingId);
        if (!building.isPresent()) {
            LOGGER.error("Building does not exist for given building Id {}", buildingId);
            throw new ServiceException(BUILDING_DOES_NOT_EXIST);
        }
        return building.get();
    }

    public static Block getBlockForBlockId(BlockRepository blockRepository, String blockId) throws ServiceException {
        Optional<Block> block = blockRepository.findBlockById(blockId);
        if (!block.isPresent()) {
            LOGGER.error("Block does not exist for given block Id {}", blockId);
            throw new ServiceException(BLOCK_DOES_NOT_EXIST);
        }
        return block.get();
    }


    public static List<Block> getBlocksForBuildingId(BlockRepository blockRepository, String buildingId) throws ServiceException {
        Optional<List<Block>> blocks = blockRepository.findBlockByBuildingId(buildingId);
        if (!blocks.isPresent()) {
            LOGGER.error("Block does not exist in given building {}", buildingId);
            throw new ServiceException(BLOCKS_DO_NOT_EXIST_IN_BUILDING);
        }
        return blocks.get();
    }

    public static void checkIfBlockExistsInBuilding(BlockRepository blockRepository, String buildingId, String blockId) throws ServiceException {
        Optional<Block> block = blockRepository.findBlockByIdAndBuildingId(blockId, buildingId);
        if(!block.isPresent()){
            LOGGER.error("Block {} does not exist in given building {}", blockId, buildingId);
            throw new ServiceException(BLOCK_DOES_NOT_EXIST_IN_BUILDING);
        }
    }

}
