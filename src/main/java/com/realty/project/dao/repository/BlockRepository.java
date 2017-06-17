package com.realty.project.dao.repository;

import com.realty.project.dao.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {

    Optional<Block> findBlockById(String id);

    Optional<Block> findBlockByIdAndBuildingId(String id, String buildingId);

    Optional<Block> findBlockByName(String name);

    Optional<Block> findBlockByBlock(String block);

    Optional<List<Block>> findBlockByBuildingId(String buildingId);
}
