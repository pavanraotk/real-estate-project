package com.realty.project.service;

import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BuildingInput;
import com.realty.project.service.output.BuildingOutput;

public interface BuildingService {
    BuildingOutput addBuilding(BuildingInput buildingInput) throws ServiceException;
    
    BuildingOutput updateBuilding(BuildingInput buildingInput) throws ServiceException;

    BuildingOutput getBuildingByName(String buildingName) throws ServiceException;

    void deleteBuilding(String buildingId) throws ServiceException;
}
