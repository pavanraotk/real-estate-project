package com.realty.project.dao.repository;

import com.realty.project.dao.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

    Optional<Unit> findById(String id);

    Optional<Unit> findByApartmentNumberAndFloorNumberAndBlockIdAndBuildingId(Integer apartmentNumber, Integer floorNumber, String blockId, String buildingId);

    Optional<Unit> findByCustomerNumber(String customerNumber);
}
