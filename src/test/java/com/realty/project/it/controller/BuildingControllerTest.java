package com.realty.project.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realty.project.AbstractTestApplication;
import com.realty.project.common.controller.BuildingRequestBuilder;
import com.realty.project.dao.model.Building;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.rest.request.BuildingRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.Optional;

import static com.realty.project.common.Constants.ADD_BUILDING_PATH;
import static com.realty.project.common.Constants.BUILDING_PATH;
import static org.junit.Assert.assertEquals;

public class BuildingControllerTest extends AbstractTestApplication {

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BuildingRepopsitory buildingRepopsitory;

    private BuildingRequest buildingRequest;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        objectMapper = new ObjectMapper();
        buildingRequest = new BuildingRequestBuilder().withData().build();
    }

    @After
    public void tearDown(){
        Optional<Building> building = buildingRepopsitory.findByName(buildingRequest.getName());
        if(building.isPresent()){
            buildingRepopsitory.delete(building.get());
        }
    }

    @Test
    public void testBuildingController() throws Exception {
        MvcResult mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
        assertEquals(200, mvcResult.getResponse().getStatus());
        mvcResult = perform(BUILDING_PATH + ADD_BUILDING_PATH, buildingRequest);
    }
}
