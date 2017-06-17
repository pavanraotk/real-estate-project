package com.realty.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realty.project.rest.response.Error;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static com.realty.project.common.Constants.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class AbstractTestController {

    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper;

    protected void assertBadRequest(int httpStatus, MvcResult mvcResult, String code, String message) throws IOException {
        assertEquals(httpStatus, mvcResult.getResponse().getStatus());
        String response = mvcResult.getResponse().getContentAsString();
        Error error = getErrorResponse(response);
        assertEquals(code, error.getCode());
        assertEquals(message, error.getMessage());
    }

    protected MvcResult perform(String path, Object request) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post(path)
                .content(getJsonString(request))
                .contentType(APPLICATION_JSON))
                .andReturn();
        return mvcResult;
    }

    private String getJsonString(Object request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return jsonRequest;
    }

    private Error getErrorResponse(String response) throws IOException {
        return objectMapper.readValue(response, Error.class);
    }

}

