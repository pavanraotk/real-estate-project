package com.realty.project.rest;

import com.realty.project.rest.response.Error;
import com.realty.project.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static com.realty.project.common.Constants.*;
import static java.util.Locale.ENGLISH;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Error> handleException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String field = getFieldInException(bindingResult);
        Error error = getError(bindingResult, field);
        return new ResponseEntity<Error>(error, BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceException.class)
    protected ResponseEntity<Error> handleException(ServiceException ex) {
        Error error = new Error();
        error.setCode(ex.getErrorId());
        error.setMessage(messageSource.getMessage(ex.getErrorId(), null, ENGLISH));
        return new ResponseEntity<Error>(error, INTERNAL_SERVER_ERROR);
    }

    private Error getError(BindingResult bindingResult, String field) {
        Optional<Error> error = empty();
        String[] codes = bindingResult.getFieldError().getCodes();
        for (String code : codes) {
            error = getError(field, code);
            if (!error.isPresent()) {
                continue;
            }
        }
        if (!error.isPresent()) {
            return createError("unknown.error", "Unknown error, please contact customer care");
        }
        return error.get();
    }

    private Optional<Error> getError(String field, String code) {
        Error error = null;
        switch (code.toUpperCase()) {
            case PATTERN:
                error = createError(field + DOES_NOT_MATCH + PATTERN.toLowerCase(), messageSource.getMessage(field + DOES_NOT_MATCH + PATTERN.toLowerCase(), null, ENGLISH));
                break;
            case NOTNULL:
            case NOTBLANK:
                error = createError(field + SHOULD_EXIST.toLowerCase(), messageSource.getMessage(field + SHOULD_EXIST, null, ENGLISH));
                break;
            case MAX:
            case MIN:
            case LENGTH:
                error = createError(field + LENGTH_NOT_MATCH, messageSource.getMessage(field + LENGTH_NOT_MATCH, null, ENGLISH));
                break;
        }
        if (error != null) {
            return of(error);
        } else {
            return empty();
        }
    }

    private Error createError(String code, String message) {
        Error unknownError = new Error();
        unknownError.setCode(code);
        unknownError.setMessage(message);
        return unknownError;
    }

    private String getFieldInException(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        String field = fieldError.getField();
        return field;
    }
}
