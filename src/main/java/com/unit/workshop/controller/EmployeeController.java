package com.unit.workshop.controller;

import com.unit.workshop.bean.EmployeeInfo;
import com.unit.workshop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public void createEmployee(@RequestBody EmployeeInfo employeeInfo) throws Exception {
        employeeService.createEmployeeInfo(employeeInfo);
    }

    @PostMapping("/getEmployeeInfo")
    public void getEmployeeInfo(@RequestBody String employeeID) {
        employeeService.getEmployeeInfo(employeeID);
    }

}
