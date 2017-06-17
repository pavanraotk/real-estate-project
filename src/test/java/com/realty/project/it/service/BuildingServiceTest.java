package com.realty.project.it.service;

import com.realty.project.common.service.BuildingInputBuilder;
import com.realty.project.AbstractTestApplication;
import com.realty.project.service.BuildingService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BuildingInput;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.realty.project.common.ErrorCodes.BUILDING_DOES_NOT_EXIST;
import static org.junit.Assert.assertEquals;

public class BuildingServiceTest extends AbstractTestApplication {

    @Autowired
    private BuildingService buildingService;

    private BuildingInput buildingInput;

    @Before
    public void setUp() {
        buildingInput = new BuildingInputBuilder().withData().build();
    }

    @Test
    public void testBuildingService() throws Exception {
        buildingService.addBuilding(buildingInput);
        assertEquals(buildingInput.getName(), buildingService.getBuildingByName(buildingInput.getName()).getName());
        buildingInput.setOneBedroomUnitsAvailable(99);
        assertEquals(buildingInput.getOneBedroomUnitsAvailable(), buildingService.updateBuilding(buildingInput).getOneBedroomUnitsAvailable());
        buildingService.deleteBuilding(buildingInput.getId());
        try {
            buildingService.getBuildingByName(buildingInput.getName());
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }
}
