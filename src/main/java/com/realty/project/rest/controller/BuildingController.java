package com.realty.project.rest.controller;

import com.realty.project.rest.BaseController;
import com.realty.project.rest.request.BuildingRequest;
import com.realty.project.rest.response.BuildingResponse;
import com.realty.project.service.BuildingService;
import com.realty.project.service.exception.ServiceException;
import com.realty.project.service.input.BuildingInput;
import com.realty.project.service.output.BuildingOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.realty.project.common.Constants.*;
import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = BUILDING_PATH)
public class BuildingController extends BaseController {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping(value = ADD_BUILDING_PATH, method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<BuildingResponse> addBuilding(@Valid @RequestBody BuildingRequest buildingRequest) throws ServiceException, MethodArgumentNotValidException {
        BuildingInput buildingInput = generateBuildingInput(buildingRequest);
        BuildingOutput buildingOutput = buildingService.addBuilding(buildingInput);
        BuildingResponse buildingResponse = generateBuildingResponse(buildingOutput);
        return new ResponseEntity<>(buildingResponse, OK);
    }

    private BuildingResponse generateBuildingResponse(BuildingOutput buildingOutput) {
        BuildingResponse buildingResponse = new BuildingResponse();
        buildingResponse.setId(buildingOutput.getId());
        buildingResponse.setName(buildingOutput.getName());
        buildingResponse.setAddress(buildingOutput.getAddress());
        buildingResponse.setBlocks(buildingOutput.getBlocks());
        buildingResponse.setOneBedroomUnitsAvailable(buildingOutput.getOneBedroomUnitsAvailable());
        buildingResponse.setTwoBedroomUnitsAvailable(buildingOutput.getTwoBedroomUnitsAvailable());
        buildingResponse.setThreeBedroomUnitsAvailable(buildingOutput.getThreeBedroomUnitsAvailable());
        buildingResponse.setPentHousesAvailable(buildingOutput.getPentHousesAvailable());
        buildingResponse.setOneBedroomUnitsTotal(buildingOutput.getOneBedroomUnitsTotal());
        buildingResponse.setTwoBedroomUnitsTotal(buildingOutput.getTwoBedroomUnitsTotal());
        buildingResponse.setThreeBedroomUnitsTotal(buildingOutput.getThreeBedroomUnitsTotal());
        buildingResponse.setPentHousesTotal(buildingOutput.getPentHousesTotal());
        return buildingResponse;
    }

    private BuildingInput generateBuildingInput(BuildingRequest buildingRequest) {
        BuildingInput buildingInput = new BuildingInput();
        buildingInput.setId(randomUUID().toString());
        buildingInput.setName(buildingRequest.getName());
        buildingInput.setAddress(buildingRequest.getAddress());
        buildingInput.setBlocks(Integer.valueOf(buildingRequest.getBlocks()));
        buildingInput.setOneBedroomUnitsAvailable(buildingRequest.getOneBedroomUnitsAvailable());
        buildingInput.setTwoBedroomUnitsAvailable(buildingRequest.getTwoBedroomUnitsAvailable());
        buildingInput.setThreeBedroomUnitsAvailable(buildingRequest.getThreeBedroomUnitsAvailable());
        buildingInput.setPentHousesAvailable(buildingRequest.getPentHousesAvailable());
        buildingInput.setOneBedroomUnitsTotal(buildingRequest.getOneBedroomUnitsTotal());
        buildingInput.setTwoBedroomUnitsTotal(buildingRequest.getTwoBedroomUnitsTotal());
        buildingInput.setThreeBedroomUnitsTotal(buildingRequest.getThreeBedroomUnitsTotal());
        buildingInput.setPentHousesTotal(buildingRequest.getPentHousesTotal());
        return buildingInput;
    }
}
