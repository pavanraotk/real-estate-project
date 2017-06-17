package com.realty.project.it.service;

import com.realty.project.common.service.BuildingInputBuilder;
import com.realty.project.AbstractTestApplication;
import com.realty.project.service.BlockService;
import com.realty.project.service.BuildingService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import com.realty.project.service.input.BuildingInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.realty.project.common.ErrorCodes.BLOCK_DOES_NOT_EXIST;
import static com.realty.project.common.TestConstants.EXCEPTION_NOT_THROWN;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BlockServiceTest extends AbstractTestApplication {

    @Autowired
    BuildingService buildingService;

    @Autowired
    BlockService blockService;

    BuildingInput buildingInput;
    BlockInput blockInput;

    @Before
    public void setUp() throws Exception {
        buildingInput = new BuildingInputBuilder().withData().build();
        blockInput = buildingInput.getBlockInputList().get(0);
        buildingService.addBuilding(buildingInput);
    }

    @After
    public void tearDown() throws ServiceException {
        buildingService.deleteBuilding(buildingInput.getId());
    }

    @Test
    public void testBlockService() throws ServiceException {
        assertEquals(blockInput.getName(), blockService.getBlock(blockInput.getId()).getName());
        assertEquals(blockInput.getName(), blockService.getBlocksOfBuilding(buildingInput.getName()).get(0).getName());
        blockInput.setOneBedroomUnitsAvailable(99);
        blockService.updateBlock(blockInput);
        assertEquals(blockInput.getOneBedroomUnitsAvailable(), blockService.getBlock(blockInput.getId()).getOneBedroomUnitsAvailable());
        blockService.deleteBlock(blockInput.getId());
        try {
            blockService.getBlock(blockInput.getId());
            fail(EXCEPTION_NOT_THROWN);
        } catch (ServiceException e) {
            assertEquals(BLOCK_DOES_NOT_EXIST, e.getErrorId());
        }
        assertEquals(of(9), of(buildingService.getBuildingByName(buildingInput.getName()).getBlocks()));
    }
}
