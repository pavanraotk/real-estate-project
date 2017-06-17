package com.realty.project.service.implementation;

import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.service.BlockService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import com.realty.project.service.output.BlockOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.realty.project.service.implementation.CommonService.*;

@Service
public class BlockServiceImpl implements BlockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceImpl.class);

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private BuildingRepopsitory buildingRepopsitory;

    @Override
    public BlockOutput addBlock(BlockInput blockInput) throws ServiceException {
        checkIfBuildingExists(buildingRepopsitory, blockInput.getBuildingId());
        Block block = blockRepository.save(generateBlock(blockInput));
        return generateBlockOutput(block);
    }

    @Override
    public List<BlockOutput> getBlocksOfBuilding(String buildingName) throws ServiceException {
        Building building = getBuilding(buildingRepopsitory, buildingName);
        List<Block> blocks = getBlocksForBuildingId(blockRepository, building.getId());
        return getBlockOutput(blocks);
    }

    @Override
    public BlockOutput updateBlock(BlockInput blockInput) throws ServiceException {
        checkIfBuildingExists(buildingRepopsitory, blockInput.getBuildingId());
        getBlock(blockInput.getId());
        Block block = blockRepository.save(generateBlock(blockInput));
        return generateBlockOutput(block);
    }

    @Override
    public BlockOutput getBlock(String blockId) throws ServiceException {
        Block block = getBlockForBlockId(blockRepository, blockId);
        return generateBlockOutput(block);
    }

    @Override
    public void deleteBlock(String blockId) throws ServiceException {
        Block block = getBlockForBlockId(blockRepository, blockId);
        Building building = getBuildingForBuildingId(buildingRepopsitory, block.getBuildingId());
        updateBuilding(building);
        blockRepository.delete(block);
    }

    private void updateBuilding(Building building) {
        building.setBlocks(building.getBlocks() - 1);
        buildingRepopsitory.save(building);
    }

    private List<BlockOutput> getBlockOutput(List<Block> blocks) {
        List<BlockOutput> blockOutputList = new ArrayList<>();
        for (Block block : blocks) {
            blockOutputList.add(generateBlockOutput(block));
        }
        return blockOutputList;
    }

    private BlockOutput generateBlockOutput(Block block) {
        BlockOutput blockOutput = new BlockOutput();
        blockOutput.setId(block.getId());
        blockOutput.setBlock(block.getBlock());
        blockOutput.setBuildingId(block.getBuildingId());
        blockOutput.setName(block.getName());
        blockOutput.setOneBedroomUnitsTotal(block.getOneBedroomUnitsTotal());
        blockOutput.setTwoBedroomUnitsTotal(block.getTwoBedroomUnitsTotal());
        blockOutput.setThreeBedroomUnitsTotal(block.getThreeBedroomUnitsTotal());
        blockOutput.setPentHousesTotal(block.getPentHousesTotal());
        blockOutput.setOneBedroomUnitsAvailable(block.getOneBedroomUnitsAvailable());
        blockOutput.setTwoBedroomUnitsAvailable(block.getTwoBedroomUnitsAvailable());
        blockOutput.setThreeBedroomUnitsAvailable(block.getThreeBedroomUnitsAvailable());
        blockOutput.setPentHousesAvailable(block.getPentHousesAvailable());
        return blockOutput;
    }

    private Block generateBlock(BlockInput blockInput) {
        Block block = new Block();
        block.setId(blockInput.getId());
        block.setName(blockInput.getName());
        block.setBlock(blockInput.getBlockId());
        block.setBuildingId(blockInput.getBuildingId());
        block.setOneBedroomUnitsAvailable(blockInput.getOneBedroomUnitsAvailable());
        block.setTwoBedroomUnitsAvailable(blockInput.getTwoBedroomUnitsAvailable());
        block.setThreeBedroomUnitsAvailable(blockInput.getThreeBedroomUnitsAvailable());
        block.setPentHousesAvailable(blockInput.getPentHousesAvailable());
        block.setOneBedroomUnitsTotal(blockInput.getOneBedroomUnitsTotal());
        block.setTwoBedroomUnitsTotal(blockInput.getTwoBedroomUnitsTotal());
        block.setThreeBedroomUnitsTotal(blockInput.getThreeBedroomUnitsTotal());
        block.setPentHousesTotal(blockInput.getPentHousesTotal());
        return block;
    }

}
