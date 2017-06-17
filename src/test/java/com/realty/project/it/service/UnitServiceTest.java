package com.realty.project.it.service;

import com.realty.project.common.dao.BlockBuilder;
import com.realty.project.common.dao.BuildingBuilder;
import com.realty.project.common.service.UnitInputBuilder;
import com.realty.project.dao.model.Block;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.AbstractTestApplication;
import com.realty.project.service.UnitService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.UnitInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.realty.project.common.ErrorCodes.UNIT_DOES_NOT_EXIST;
import static com.realty.project.common.TestConstants.EXCEPTION_NOT_THROWN;
import static com.realty.project.common.UnitType.ONE_BEDROOM_UNIT;
import static java.util.OptionalInt.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UnitServiceTest extends AbstractTestApplication {

    @Autowired
    private UnitService unitService;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private BuildingRepopsitory buildingRepopsitory;

    private Building building;
    private Block block;
    private UnitInput unitInput;

    @Before
    public void setUp() throws Exception {
        block = new BlockBuilder().withData().build();
        building = new BuildingBuilder().withData().setId(block.getBuildingId()).build();
        unitInput = new UnitInputBuilder().withData().setBlockId(block.getId()).setBuildingId(block.getBuildingId()).build();
        buildingRepopsitory.save(building);
        blockRepository.save(block);
    }

    @After
    public void tearDown() throws Exception {
        blockRepository.delete(block);
        buildingRepopsitory.delete(building);
    }

    @Test
    public void testUnitService() throws Exception {
        unitService.addUnit(unitInput);
        assertEquals(unitInput.getId(), unitService.getUnit(unitInput.getId()).getId());
        assertEquals(unitInput.getId(), unitService.getUnitForCustomerNumberBlockIdBuildingId(unitInput.getCustomerNumber(), unitInput.getBlockId(), unitInput.getBuildingId()).getId());
        unitInput.setUnitType(ONE_BEDROOM_UNIT);
        unitService.updateUnit(unitInput);
        assertEquals(unitInput.getUnitType(), unitService.getUnit(unitInput.getId()).getUnitType());
        unitService.deleteUnit(unitInput.getId());
        Block block = blockRepository.findBlockById(unitInput.getBlockId()).get();
        assertEquals(of(101), of(block.getOneBedroomUnitsAvailable()));
        try {
            unitService.getUnit(unitInput.getId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(UNIT_DOES_NOT_EXIST, e.getErrorId());
        }
    }

}