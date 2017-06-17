package com.realty.project.dao.repository;

import com.realty.project.dao.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepopsitory extends JpaRepository<Building, String> {

    Optional<Building> findById(String id);

    Optional<Building> findByName(String name);

    Optional<Building> findByAddress(String address);
}
