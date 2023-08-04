package com.unit.workshop.dao.Imple;

import com.unit.workshop.bean.EmployeeInfo;
import com.unit.workshop.dao.EmployeeDataAccess;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDataAccessImple implements EmployeeDataAccess {

    Map<String,EmployeeInfo> emploeeInfoMap = new HashMap<>();

    @Override
    public EmployeeInfo getEmployeeInfo(String employeeID) {
        return Optional.ofNullable(emploeeInfoMap.get(employeeID)).orElse(null);
    }

    @Override
    public void insertEmployeeInfo(EmployeeInfo employeeInfo) {
        emploeeInfoMap.put(employeeInfo.getEmployeeID(),employeeInfo);
    }
}
