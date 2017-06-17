package com.realty.project.it.dao.repository;

import com.realty.project.dao.model.Building;
import com.realty.project.common.dao.BuildingBuilder;
import com.realty.project.dao.repository.BuildingRepopsitory;
import com.realty.project.AbstractTestApplication;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class BuildingRepositoryTest extends AbstractTestApplication {

    @Autowired
    public BuildingRepopsitory buildingRepopsitory;

    private Building building;

    @Before
    public void setUp() {
        building = new BuildingBuilder().withData().build();
    }

    @Test
    public void testPropertiesRepository() {
        buildingRepopsitory.save(building);
        assertEquals(building, buildingRepopsitory.findById(building.getId()).get());
        building.setOneBedroomUnitsAvailable(99);
        buildingRepopsitory.save(building);
        assertEquals(building.getOneBedroomUnitsAvailable(), buildingRepopsitory.findById(building.getId()).get().getOneBedroomUnitsAvailable());
        assertEquals(building, buildingRepopsitory.findById(building.getId()).get());
        assertEquals(building, buildingRepopsitory.findByName(building.getName()).get());
        assertEquals(building, buildingRepopsitory.findByAddress(building.getAddress()).get());
        buildingRepopsitory.delete(building);
        assertFalse(buildingRepopsitory.findById(building.getId()).isPresent());
    }


}