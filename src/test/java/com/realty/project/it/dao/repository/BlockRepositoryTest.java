package com.realty.project.it.dao.repository;

import com.realty.project.dao.model.Block;
import com.realty.project.common.dao.BlockBuilder;
import com.realty.project.dao.repository.BlockRepository;
import com.realty.project.AbstractTestApplication;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class BlockRepositoryTest extends AbstractTestApplication {

    @Autowired
    private BlockRepository blockRepository;

    private Block block;

    @Before
    public void setUp() {
        block = new BlockBuilder().withData().build();
    }

    @Test
    public void testBlockRepository() {
        blockRepository.save(block);
        assertEquals(block, blockRepository.findBlockById(block.getId()).get());
        assertEquals(block, blockRepository.findBlockByIdAndBuildingId(block.getId(), block.getBuildingId()).get());
        assertEquals(block, blockRepository.findBlockByName(block.getName()).get());
        assertEquals(block, blockRepository.findBlockByBlock(block.getBlock()).get());
        block.setOneBedroomUnitsAvailable(99);
        blockRepository.save(block);
        assertEquals(block, blockRepository.findBlockById(block.getId()).get());
        assertEquals(block, blockRepository.findBlockByBuildingId(block.getBuildingId()).get().get(0));
        blockRepository.delete(block);
        assertFalse(blockRepository.findBlockById(block.getId()).isPresent());
    }
}