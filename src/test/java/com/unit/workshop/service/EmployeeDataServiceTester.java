package com.unit.workshop.service;

import com.unit.workshop.bean.EmployeeInfo;
import com.unit.workshop.dao.EmployeeDataAccess;
import com.unit.workshop.service.imple.EmployeeServiceImple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeDataServiceTester {
    @InjectMocks
    EmployeeService target = new EmployeeServiceImple();

    @Mock
    EmployeeDataAccess mockDao;


    @Test
    @DisplayName("取得員工資料_成功案例")
    public void getEmployeeSuccessTestCase() {
        // Arrange
        EmployeeInfo mockResponse = new EmployeeInfo();
        mockResponse.setEmployeeID("00001");
        mockResponse.setName("Wang");
        mockResponse.setMobile("091234567");

        Mockito.when(mockDao.getEmployeeInfo(any(String.class))).thenReturn(mockResponse);

        // Act
        EmployeeInfo response = target.getEmployeeInfo("00001");

        // Assert
        assertEquals(mockResponse.getEmployeeID(),response.getEmployeeID());
        assertEquals(mockResponse.getName(),response.getName());
        assertEquals(mockResponse.getMobile(),response.getMobile());

    }

    @Test
    @DisplayName("取得員工資料_失敗案例")
    public void getEmployeeFailTestCase() {
        // Arrange
        Mockito.when(mockDao.getEmployeeInfo(any(String.class))).thenReturn(null);


        // Assert
        NullPointerException exception = assertThrows( NullPointerException.class
                                         // Act
                                       , () -> target.getEmployeeInfo("00001"));

        // Assert
        assertEquals("Employee Doesn't Exist", exception.getMessage());

    }


    @Test
    @DisplayName("建立員工資料成功案例")
    public void createEmployeeInfoSuccessTestCase() throws Exception {
        // Arrange
        Mockito.when(mockDao.getEmployeeInfo(any(String.class))).thenReturn(null);
        Mockito.doNothing().when(mockDao).insertEmployeeInfo(any());


        // Act
        EmployeeInfo input = new EmployeeInfo();
        input.setEmployeeID("00001");
        input.setName("Wang");
        input.setMobile("091234567");
        target.createEmployeeInfo(input);

        // Assert
        Mockito.verify(mockDao,times(1)).insertEmployeeInfo(any());

    }


    @Test
    @DisplayName("建立員工資料失敗案例")
    public void createEmployeeInfoFailTestCase() throws Exception {
        // Arrange
        Mockito.when(mockDao.getEmployeeInfo(any(String.class))).thenReturn(new EmployeeInfo());
        Mockito.doNothing().when(mockDao).insertEmployeeInfo(any());


        // Act
        EmployeeInfo input = new EmployeeInfo();
        input.setEmployeeID("00001");
        input.setName("Wang");
        input.setMobile("091234567");


        Exception exception = assertThrows( Exception.class
                                        // Act
                                       , () -> target.createEmployeeInfo(input));

        // Assert
        Mockito.verify(mockDao,times(0)).insertEmployeeInfo(any());
        assertEquals("Employee Is Exist！！！",exception.getMessage());

    }

}
