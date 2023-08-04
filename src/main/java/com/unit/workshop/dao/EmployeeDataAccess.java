package com.unit.workshop.dao;

import com.unit.workshop.bean.EmployeeInfo;

public interface EmployeeDataAccess{

    EmployeeInfo getEmployeeInfo(String employeeID);


    void insertEmployeeInfo(EmployeeInfo employeeInfo);
}
