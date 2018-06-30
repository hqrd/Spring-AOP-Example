package com.hqrd.springaop.dao;

import com.hqrd.springaop.model.EmployeeVO;

import java.util.List;

public interface EmployeeDAO {
    List<EmployeeVO> getAllEmployees();
}