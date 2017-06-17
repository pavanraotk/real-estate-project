package com.realty.project.it.dao.repository;

import com.realty.project.dao.model.Unit;
import com.realty.project.common.dao.UnitBuilder;
import com.realty.project.dao.repository.UnitRepository;
import com.realty.project.AbstractTestApplication;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class UnitRepositoryTest extends AbstractTestApplication {

    @Autowired
    UnitRepository unitRepository;

    Unit unit;

    @Before
    public void setUp() throws Exception {
        unit = new UnitBuilder().withData().build();
    }

    @Test
    public void testUnitRepository() {
        unitRepository.save(unit);
        assertEquals(unit, unitRepository.findById(unit.getId()).get());
        assertEquals(unit, unitRepository.findByApartmentNumberAndFloorNumberAndBlockIdAndBuildingId(unit.getApartmentNumber(), unit.getFloorNumber(), unit.getBlockId(), unit.getBuildingId()).get());
        assertEquals(unit, unitRepository.findByCustomerNumber(unit.getCustomerNumber()).get());
        unit.setCustomerNumber("+919844460190");
        unitRepository.save(unit);
        assertEquals(unit, unitRepository.findById(unit.getId()).get());
        unitRepository.delete(unit);
        assertFalse(unitRepository.findById(unit.getId()).isPresent());
    }
}