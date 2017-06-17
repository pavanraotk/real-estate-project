package com.realty.project.service;

import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.UnitInput;
import com.realty.project.service.output.UnitOutput;

public interface UnitService {
    UnitOutput addUnit(UnitInput unitInput) throws ServiceException;

    UnitOutput updateUnit(UnitInput unitInput) throws ServiceException;

    UnitOutput getUnit(String unitId) throws ServiceException;

    UnitOutput getUnitForCustomerNumberBlockIdBuildingId(String customerNumber, String blockId, String buildingId) throws ServiceException;

    void deleteUnit(String unitId) throws ServiceException;
}
