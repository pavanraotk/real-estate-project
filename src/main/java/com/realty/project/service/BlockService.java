package com.realty.project.service;

import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BlockInput;
import com.realty.project.service.output.BlockOutput;

import java.util.List;

public interface BlockService {

    BlockOutput addBlock(BlockInput blockInput) throws ServiceException;

    List<BlockOutput> getBlocksOfBuilding(String buildingName) throws ServiceException;

    BlockOutput updateBlock(BlockInput blockInput) throws ServiceException;

    BlockOutput getBlock(String blockId) throws ServiceException;

    void deleteBlock(String blockId) throws ServiceException;
}
