package com.realty.project.service.implementation;

import com.realty.project.common.dao.BlockBuilder;
import com.realty.project.common.dao.BuildingBuilder;
import com.realty.project.common.dao.UnitBuilder;
import com.realty.project.common.service.UnitInputBuilder;
import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.model.Unit;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.dao.repository.UnitRepository;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.UnitInput;
import com.realty.project.service.output.UnitOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.realty.project.common.ErrorCodes.*;
import static com.realty.project.common.TestConstants.EXCEPTION_NOT_THROWN;
import static com.realty.project.common.UnitType.ONE_BEDROOM_UNIT;
import static com.realty.project.common.UnitType.PENT_HOUSE;
import static com.realty.project.common.UnitType.TWO_BEDROOM_UNIT;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UnitServiceImplTest {

    @InjectMocks
    private UnitServiceImpl unitServiceImpl;

    @Mock
    private BuildingRepopsitory buildingRepopsitory;

    @Mock
    private BlockRepository blockRepository;

    @Mock
    private UnitRepository unitRepository;

    private UnitInput unitInput;
    private Building building;
    private Block block;
    private Unit unit;

    @Before
    public void setUp() {
        initMocks(this);
        unitInput = new UnitInputBuilder().withData().build();
        building = new BuildingBuilder().withData().setId(unitInput.getBuildingId()).build();
        block = new BlockBuilder().withData().setId(unitInput.getBlockId()).build();
        unit = new UnitBuilder().withData().setBlockId(block.getId()).setBuildingId(building.getId()).setId(unitInput.getId()).build();
    }

    @Test
    public void testAddUnit() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.save(any(Unit.class))).thenReturn(unit);
        UnitOutput unitOutput = unitServiceImpl.addUnit(unitInput);
        verify(unitRepository).save(any(Unit.class));
        assertEquals(unitInput.getId(), unitOutput.getId());
    }

    @Test
    public void testAddUnitBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.addUnit(unitInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testAddUnitBlockDoesNotExistInBuilding() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.addUnit(unitInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST_IN_BUILDING, e.getErrorId());
        }
    }

    @Test
    public void testUpdateUnit() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        UnitOutput unitOutput = unitServiceImpl.updateUnit(unitInput);
        verify(unitRepository).save(any(Unit.class));
        assertEquals(unitInput.getId(), unitOutput.getId());
    }

    @Test
    public void testUpdateUnitBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.updateUnit(unitInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testUpdateUnitBlockDoesNotExistInBuilding() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.updateUnit(unitInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST_IN_BUILDING, e.getErrorId());
        }
    }

    @Test
    public void testUpdateUnitDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findById(unitInput.getId())).thenReturn(empty());
        try {
            unitServiceImpl.updateUnit(unitInput);
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetUnit() throws Exception {
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        UnitOutput unitOutput = unitServiceImpl.getUnit(unitInput.getId());
        assertEquals(unitInput.getId(), unitOutput.getId());
    }

    @Test
    public void testGetUnitDoesNotExist() throws Exception {
        when(unitRepository.findById(unitInput.getId())).thenReturn(empty());
        try {
            unitServiceImpl.getUnit(unitInput.getId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingId() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findByCustomerNumber(unitInput.getCustomerNumber())).thenReturn(of(unit));
        UnitOutput unitOutput = unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
        assertEquals(unitInput.getId(), unitOutput.getId());
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingIdBuildingDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingIdBlockDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(empty());
        try {
            unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST_IN_BUILDING, e.getErrorId());
        }
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingIdUnitDoesNotExistInBuilding() throws Exception {
        unit = new UnitBuilder().withData().setBuildingId(randomUUID().toString()).build();
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findByCustomerNumber(unitInput.getCustomerNumber())).thenReturn(of(unit));
        try {
            unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST_IN_BUILDING, e.getErrorId());
        }
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingIdUnitDoesNotExistInBlock() throws Exception {
        unit = new UnitBuilder().withData().setBuildingId(unitInput.getBuildingId().toString()).setBlockId(randomUUID().toString()).build();
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findByCustomerNumber(unitInput.getCustomerNumber())).thenReturn(of(unit));
        try {
            unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST_IN_BLOCK, e.getErrorId());
        }
    }

    @Test
    public void testGetUnitForCustomerNumberBlockIdBuildingIdUnitDoesNotExist() throws Exception {
        when(buildingRepopsitory.findById(unitInput.getBuildingId())).thenReturn(of(building));
        when(blockRepository.findBlockByIdAndBuildingId(unitInput.getBlockId(), unitInput.getBuildingId())).thenReturn(of(block));
        when(unitRepository.findByCustomerNumber(unitInput.getCustomerNumber())).thenReturn(empty());
        try {
            unitServiceImpl.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId());
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testDeleteUnitThreeBedroomUnits() throws Exception {
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        when(blockRepository.findBlockById(unit.getBlockId())).thenReturn(of(block));
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        unitServiceImpl.deleteUnit(unitInput.getId());
        verify(unitRepository).delete(unit);
    }

    @Test
    public void testDeleteUnitTwoBedroomUnits() throws Exception {
        unit = new UnitBuilder().withData().setType(TWO_BEDROOM_UNIT).build();
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        when(blockRepository.findBlockById(unit.getBlockId())).thenReturn(of(block));
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        unitServiceImpl.deleteUnit(unitInput.getId());
        verify(unitRepository).delete(unit);
    }

    @Test
    public void testDeleteUnitOneBedroomUnits() throws Exception {
        unit = new UnitBuilder().withData().setType(ONE_BEDROOM_UNIT).build();
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        when(blockRepository.findBlockById(unit.getBlockId())).thenReturn(of(block));
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        unitServiceImpl.deleteUnit(unitInput.getId());
        verify(unitRepository).delete(unit);
    }

    @Test
    public void testDeleteUnitPentHouse() throws Exception {
        unit = new UnitBuilder().withData().setType(PENT_HOUSE).build();
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        when(blockRepository.findBlockById(unit.getBlockId())).thenReturn(of(block));
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        unitServiceImpl.deleteUnit(unitInput.getId());
        verify(unitRepository).delete(unit);
    }

    @Test
    public void testDeleteUnitDoesNotExist() throws Exception {
        when(unitRepository.findById(unitInput.getId())).thenReturn(empty());
        try {
            unitServiceImpl.deleteUnit(unitInput.getId());
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testDeleteUnitBlockDoesNotExist() throws Exception {
        when(unitRepository.findById(unitInput.getId())).thenReturn(of(unit));
        when(blockRepository.findBlockById(unit.getBlockId())).thenReturn(empty());
        try {
            unitServiceImpl.deleteUnit(unitInput.getId());
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST, e.getErrorId());
        }
    }

}