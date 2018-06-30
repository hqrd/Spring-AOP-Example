package com.hqrd.springaop.controller;

import com.hqrd.springaop.annotation.CustomController;
import com.hqrd.springaop.model.EmployeeVO;
import com.hqrd.springaop.service.EmployeeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Api(description = "Operations pertaining to employees")
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EmployeeManager manager;

    @Autowired
    public EmployeeController(EmployeeManager manager) {
        this.manager = manager;
    }

    @CustomController(
            endpoint = {"list"}, httpMethod = RequestMethod.GET, apiName = "View a list of employees",
            params = {"onlyOne", "test"},
            apiDoc = "Returns a list of all employees.",
            apiResponses = {@ApiResponse(code = 417, message = "Exception raised by AOP code")}
    )
    public List<EmployeeVO> getAllEmployees(@ApiParam(value = "Set as 'true' to only return one random employee", required = true) @RequestParam("onlyOne") String onlyOne,
                                            @ApiParam(value = "AOP test variable, set as 'bug' for exception", required = true) @RequestParam("test") String test) {
        List<EmployeeVO> list = manager.getAllEmployees();
        if (onlyOne.equals("true")) {
            Collections.shuffle(list);
            list = Collections.singletonList(list.get(0));
        }
        if (test.equals("bug")) return null;
        LOGGER.debug((onlyOne.equals("true") ? "Only one " : "") + "getAllEmployees returned : " + list);
        return list;
    }
}