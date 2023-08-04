package com.unit.workshop.service.imple;

import com.unit.workshop.bean.EmployeeInfo;
import com.unit.workshop.dao.EmployeeDataAccess;
import com.unit.workshop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImple implements EmployeeService {

    @Autowired
    EmployeeDataAccess employeeDataAccess;


    @Override
    public void createEmployeeInfo(EmployeeInfo employeeInfo) throws Exception {

        if(null != employeeDataAccess.getEmployeeInfo(employeeInfo.getEmployeeID())) {
            throw new Exception("Employee Is Exist！！！");
        }

        employeeDataAccess.insertEmployeeInfo(employeeInfo);

    }

    @Override
    public EmployeeInfo getEmployeeInfo(String employeeID) {

       return Optional.ofNullable(employeeDataAccess.getEmployeeInfo((employeeID)))
                      .orElseThrow( () -> new NullPointerException("Employee Doesn't Exist"));
    }
}
