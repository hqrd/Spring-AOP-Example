package com.hqrd.springaop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hqrd
 */
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class CustomException extends RuntimeException {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public CustomException(String message) {
        super(message);
        LOGGER.error(message);
    }
}