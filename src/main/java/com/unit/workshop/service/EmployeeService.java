package com.unit.workshop.service;

import com.unit.workshop.bean.EmployeeInfo;

public interface EmployeeService {


    void createEmployeeInfo(EmployeeInfo employeeInfo) throws Exception;


    EmployeeInfo getEmployeeInfo(String employeeID);


     double calculateSalaryForEmployee(String employeeId);
}
