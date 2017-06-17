package com.realty.project.service.implementation;

import com.realty.project.common.dao.BlockBuilder;
import com.realty.project.common.service.BlockInputBuilder;
import com.realty.project.common.dao.BuildingBuilder;
import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.realty.project.common.ErrorCodes.*;
import static com.realty.project.common.TestConstants.EXCEPTION_NOT_THROWN;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BlockServiceImplTest {

    @InjectMocks
    private BlockServiceImpl blockServiceImpl;

    @Mock
    private BlockRepository blockRepository;

    @Mock
    private BuildingRepopsitory buildingRepopsitory;

    private BlockInput blockInput;
    private Building building;
    private Block block;
    private List<Block> blocks;

    @Before
    public void setUp() {
        initMocks(this);
        blockInput = new BlockInputBuilder().withData().build();
        building = new BuildingBuilder().withData().setId(blockInput.getBuildingId()).build();
        block = new BlockBuilder().withData().setId(blockInput.getId()).setBlockId(blockInput.getId()).setBuildingId(blockInput.getBuildingId()).build();
        blocks = new ArrayList<>();
        blocks.add(block);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddBlockSuccess() throws Exception {
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        assertEquals(blockInput.getId(), blockServiceImpl.addBlock(blockInput).getId());
    }

    @Test
    public void testAddBlockBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(empty());
        try {
            blockServiceImpl.addBlock(blockInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetBlocksOfBuildingSuccess() throws Exception {
        when(buildingRepopsitory.findByName(building.getName())).thenReturn(of(building));
        when(blockRepository.findBlockByBuildingId(building.getId())).thenReturn(of(blocks));
        assertEquals(blockInput.getId(), blockServiceImpl.getBlocksOfBuilding(building.getName()).get(0).getId());
    }

    @Test
    public void testGetBlocksOfBuildingBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findByName(building.getName())).thenReturn(empty());
        try {
            blockServiceImpl.getBlocksOfBuilding(building.getName());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetBlocksOfBuildingBlocksDoNotExist() throws Exception {
        when(buildingRepopsitory.findByName(building.getName())).thenReturn(of(building));
        when(blockRepository.findBlockByBuildingId(building.getId())).thenReturn(empty());
        try {
            blockServiceImpl.getBlocksOfBuilding(building.getName());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCKS_DO_NOT_EXIST_IN_BUILDING, e.getErrorId());
        }
    }

    @Test
    public void testUpdateBlock() throws Exception {
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(of(block));
        block.setOneBedroomUnitsAvailable(99);
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        assertEquals(block.getOneBedroomUnitsAvailable(), blockServiceImpl.updateBlock(blockInput).getOneBedroomUnitsAvailable());
    }

    @Test
    public void testUpdateBlockBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(empty());
        try {
            blockServiceImpl.updateBlock(blockInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testUpdateBlockDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(empty());
        try {
            blockServiceImpl.updateBlock(blockInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetBlockSuccess() throws Exception {
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(of(block));
        assertEquals(blockInput.getId(), blockServiceImpl.getBlock(blockInput.getId()).getId());
    }

    @Test
    public void testGetBlockDoesNotExist() throws Exception {
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(empty());
        try {
            blockServiceImpl.getBlock(blockInput.getId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testDeleteBlockSuccess() throws Exception {
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(of(block));
        doNothing().when(blockRepository).delete(block);
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(of(building));
        building.setBlocks(building.getBlocks() - 1);
        when(buildingRepopsitory.save(building)).thenReturn(building);
        blockServiceImpl.deleteBlock(blockInput.getId());
    }

    @Test
    public void testDeleteBlockDoesNotExist() throws Exception {
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(empty());
        try{
            blockServiceImpl.deleteBlock(blockInput.getId());
            fail(EXCEPTION_NOT_THROWN);
        }catch(ServiceException e){
            assertEquals(BLOCK_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testDeleteBlockBuildingDoesNotExist() throws Exception {
        when(blockRepository.findBlockById(blockInput.getId())).thenReturn(of(block));
        when(buildingRepopsitory.findById(blockInput.getBuildingId())).thenReturn(empty());
        try{
            blockServiceImpl.deleteBlock(blockInput.getId());
        }catch(ServiceException e){
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }
}