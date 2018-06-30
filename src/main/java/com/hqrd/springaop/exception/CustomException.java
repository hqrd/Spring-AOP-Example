package com.hqrd.springaop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Julien Pruvost
 */
public class CustomException extends RuntimeException {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public CustomException(String message) {
        super(message);
    }
}