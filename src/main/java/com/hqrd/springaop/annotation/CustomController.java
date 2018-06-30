package com.hqrd.springaop.annotation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hqrd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@CrossOrigin("*")
@RequestMapping
@ApiOperation("")
@ApiResponses({})
public @interface CustomController {
    @AliasFor(annotation = RequestMapping.class, attribute = "method")
    RequestMethod[] httpMethod();

    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] endpoint();

    @AliasFor(annotation = RequestMapping.class, attribute = "params")
    String[] params() default {};

    @AliasFor(annotation = ApiOperation.class, attribute = "value")
    String apiName();

    @AliasFor(annotation = ApiOperation.class, attribute = "notes")
    String apiDoc();

    @AliasFor(annotation = ApiResponses.class, attribute = "value")
    ApiResponse[] apiResponses();
}
