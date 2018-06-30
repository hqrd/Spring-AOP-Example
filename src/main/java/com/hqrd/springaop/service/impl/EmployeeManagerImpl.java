package com.hqrd.springaop.service.impl;

import com.hqrd.springaop.dao.EmployeeDAO;
import com.hqrd.springaop.model.EmployeeVO;
import com.hqrd.springaop.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

    private final EmployeeDAO dao;

    @Autowired
    public EmployeeManagerImpl(EmployeeDAO dao) {
        this.dao = dao;
    }

    public List<EmployeeVO> getAllEmployees() {
        return dao.getAllEmployees();
    }
}
