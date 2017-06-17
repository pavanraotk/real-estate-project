package com.realty.project.service.implementation;

import com.realty.project.dao.model.Building;
import com.realty.project.common.dao.BuildingBuilder;
import com.realty.project.common.service.BuildingInputBuilder;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.service.BlockService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import com.realty.project.service.input.BuildingInput;
import com.realty.project.service.output.BlockOutput;
import com.realty.project.service.output.BuildingOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.realty.project.common.ErrorCodes.BUILDING_ALREADY_EXISTS;
import static com.realty.project.common.ErrorCodes.BUILDING_DOES_NOT_EXIST;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuildingServiceImplTest {

    @InjectMocks
    private BuildingServiceImpl buildingServiceImpl;

    @Mock
    private BlockService blockService;

    @Mock
    private BuildingRepopsitory buildingRepopsitory;

    private BuildingInput buildingInput;
    private Optional<Building> existingBuilding;
    private Building building;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        buildingInput = new BuildingInputBuilder().withData().build();
        building = new BuildingBuilder().withData().setId(buildingInput.getId()).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddBuildingSuccess() throws Exception {
        when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(empty());
        when(buildingRepopsitory.save(building)).thenReturn(building);
        when(blockService.addBlock(any(BlockInput.class))).thenReturn(any(BlockOutput.class));
        BuildingOutput buildingOutput = buildingServiceImpl.addBuilding(buildingInput);
        assertEquals(buildingInput.getId(), buildingOutput.getId());
    }

    @Test
    public void testAddBuildingThrowsBuildingAlreadyExistsException() throws Exception {
        try {
            when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(of(building));
            buildingServiceImpl.addBuilding(buildingInput);
            fail("Exception not thrown");
        } catch (ServiceException e) {
            assertEquals(BUILDING_ALREADY_EXISTS, e.getErrorId());
        }
    }

    @Test
    public void testUpdateBuilding() throws Exception {
        when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(of(building));
        when(buildingRepopsitory.save(any(Building.class))).thenReturn(building).thenReturn(building);
        BuildingOutput buildingOutput = buildingServiceImpl.updateBuilding(buildingInput);
        assertEquals(buildingInput.getId(), buildingOutput.getId());
    }

    @Test
    public void testUpdateBuildingThrowsBuildingDoesNotExistException() throws Exception {
        try {
            when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(empty());
            buildingServiceImpl.updateBuilding(buildingInput);
            fail("Exception not thrown");
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testGetBuilding() throws Exception {
        when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(of(building));
        BuildingOutput buildingOutput = buildingServiceImpl.getBuildingByName(buildingInput.getName());
        assertEquals(buildingInput.getId(), buildingOutput.getId());
    }

    @Test
    public void testGetBuildingThrowsBuildingDoesNotExistException() throws Exception {
        try {
            when(buildingRepopsitory.findByName(buildingInput.getName())).thenReturn(empty());
            buildingServiceImpl.getBuildingByName(buildingInput.getName());
            fail("Exception not thrown");
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }

    @Test
    public void testDeleteBuilding() throws Exception {
        when(buildingRepopsitory.findById(anyString())).thenReturn(of(building));
        doNothing().when(buildingRepopsitory).delete(building);
        buildingServiceImpl.deleteBuilding(buildingInput.getId());
    }

    @Test
    public void testDeleteBuildingThrowsBuildingDoesNotExistException() throws Exception {
        try {
            when(buildingRepopsitory.findById(anyString())).thenReturn(empty());
            buildingServiceImpl.deleteBuilding(building.getId());
            fail("Exception not thrown");
        } catch (ServiceException e) {
            assertEquals(BUILDING_DOES_NOT_EXIST, e.getErrorId());
        }
    }
}