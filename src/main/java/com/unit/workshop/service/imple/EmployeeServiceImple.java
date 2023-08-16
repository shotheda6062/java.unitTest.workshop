package com.unit.workshop.service.imple;

import com.unit.workshop.bean.EmployeeInfo;
import com.unit.workshop.dao.EmployeeDataAccess;
import com.unit.workshop.dao.WorkHoursDataAccess;
import com.unit.workshop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImple implements EmployeeService {

    private static final int MAX_REGULAR_HOURS = 120;
    private static final double OVERTIME_MULTIPLIER = 1.2;

    @Autowired
    EmployeeDataAccess employeeDataAccess;

    @Autowired
    WorkHoursDataAccess workHoursDataAccess;

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

    @Override
    public double calculateSalaryForEmployee(String employeeId) {

        int hoursWorked = workHoursDataAccess.findWorkHoursById(employeeId);
        EmployeeInfo employeeInfo = getEmployeeInfo(employeeId);

        if (hoursWorked > MAX_REGULAR_HOURS) {
            int regularHours = MAX_REGULAR_HOURS;
            int overtimeHours = hoursWorked - MAX_REGULAR_HOURS;

            double salary = (regularHours * employeeInfo.getHourlyRate()) + (overtimeHours * employeeInfo.getHourlyRate() * OVERTIME_MULTIPLIER);
            return salary;
        } else {
            return hoursWorked * employeeInfo.getHourlyRate();
        }

    }
}
