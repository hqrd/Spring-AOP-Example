package com.hqrd.springaop.service;

import com.hqrd.springaop.model.EmployeeVO;

import java.util.List;

public interface EmployeeManager {
    List<EmployeeVO> getAllEmployees();
}
