package com.realty.project.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realty.project.AbstractTestController;
import com.realty.project.common.controller.BuildingRequestBuilder;
import com.realty.project.common.service.BuildingOutputBuilder;
import com.realty.project.rest.request.BuildingRequest;
import com.realty.project.service.BuildingService;
import com.realty.project.service.input.BuildingInput;
import com.realty.project.service.output.BuildingOutput;
import com.realty.project.service.exception.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static com.realty.project.common.Constants.ADD_BUILDING_PATH;
import static com.realty.project.common.Constants.BUILDING_PATH;
import static com.realty.project.common.ErrorCodes.BUILDING_ALREADY_EXISTS;
import static com.realty.project.common.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuildingControllerTest extends AbstractTestController {

    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;

    @Mock
    private MessageSource messageSource;

    private BuildingOutput buildingOutput;
    private BuildingRequest buildingRequest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(buildingController).build();
        buildingRequest = new BuildingRequestBuilder().withData().build();
        buildingOutput = new BuildingOutputBuilder().withData().build();
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddBuildingSuccess() throws Exception {
        when(buildingService.addBuilding(any(BuildingInput.class))).thenReturn(buildingOutput);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testAddBuildingBlockInvalid() throws Exception {
        buildingRequest.setBlocks(BLOCKS_INVALID);
        when(messageSource.getMessage(BLOCKS_DOES_NOT_MATCH_PATTERN_CODE, null, Locale.ENGLISH)).thenReturn(BLOCKS_DOES_NOT_MATCH_PATTERN_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400, mvcResult, BLOCKS_DOES_NOT_MATCH_PATTERN_CODE, BLOCKS_DOES_NOT_MATCH_PATTERN_MESSAGE);
    }

    @Test
    public void testAddBuildingNameNull() throws Exception {
        buildingRequest.setName(null);
        when(messageSource.getMessage(NAME_SHOULD_EXIST_CODE, null, Locale.ENGLISH)).thenReturn(NAME_SHOULD_EXIST_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400, mvcResult, NAME_SHOULD_EXIST_CODE, NAME_SHOULD_EXIST_MESSAGE);
    }

    @Test
    public void testAddBuildingNameBlank() throws Exception {
        buildingRequest.setName("");
        when(messageSource.getMessage(NAME_LENGTH_NOT_MATCH_CODE, null, Locale.ENGLISH)).thenReturn(NAME_LENGTH_NOT_MATCH_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400, mvcResult, NAME_LENGTH_NOT_MATCH_CODE, NAME_LENGTH_NOT_MATCH_MESSAGE);
    }

    @Test
    public void testAddBuildingNameExceedingLength() throws Exception {
        buildingRequest.setName(NAME_LENGTH_EXCEEDING_MAX_LENGTH);
        when(messageSource.getMessage(NAME_LENGTH_NOT_MATCH_CODE, null, Locale.ENGLISH)).thenReturn(NAME_LENGTH_NOT_MATCH_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400, mvcResult, NAME_LENGTH_NOT_MATCH_CODE, NAME_LENGTH_NOT_MATCH_MESSAGE);
    }

    @Test
    public void testAddBuildingAddressNull() throws Exception {
        buildingRequest.setAddress(null);
        when(messageSource.getMessage(ADDRESS_SHOULD_EXIST_CODE, null, Locale.ENGLISH)).thenReturn(ADDRESS_SHOULD_EXIST_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400, mvcResult, ADDRESS_SHOULD_EXIST_CODE, ADDRESS_SHOULD_EXIST_MESSAGE);
    }

    @Test
    public void testAddBuildingAddressBlank() throws Exception {
        buildingRequest.setAddress("");
        when(messageSource.getMessage(ADDRESS_LENGTH_NOT_MATCH_CODE, null, Locale.ENGLISH)).thenReturn(ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400,mvcResult, ADDRESS_LENGTH_NOT_MATCH_CODE, ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
    }

    @Test
    public void testAddBuildingAddressExceedingLength() throws Exception {
        buildingRequest.setAddress(EXCEEDING_ADDRESS_LENGTH);
        when(messageSource.getMessage(ADDRESS_LENGTH_NOT_MATCH_CODE, null, Locale.ENGLISH)).thenReturn(ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400,mvcResult, ADDRESS_LENGTH_NOT_MATCH_CODE, ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
    }

    @Test
    public void testAddBuildingAddressReceedingLength() throws Exception {
        buildingRequest.setAddress(RECEEDING_LENGTH_ADDRESS);
        when(messageSource.getMessage(ADDRESS_LENGTH_NOT_MATCH_CODE, null, Locale.ENGLISH)).thenReturn(ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(400,mvcResult, ADDRESS_LENGTH_NOT_MATCH_CODE, ADDRESS_LENGTH_NOT_MATCH_MESSAGE);
    }

    @Test
    public void testAddBuildingThrowsServiceException() throws Exception {
        when(buildingService.addBuilding(any(BuildingInput.class))).thenThrow(new ServiceException(BUILDING_ALREADY_EXISTS));
        when(messageSource.getMessage(BUILDING_ALREADY_EXISTS, null, Locale.ENGLISH)).thenReturn(BUILDING_ALREADY_EXISTS_MESSAGE);
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertBadRequest(500, mvcResult, BUILDING_ALREADY_EXISTS, BUILDING_ALREADY_EXISTS_MESSAGE);
    }

}