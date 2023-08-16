package com.unit.workshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDoubleStubExample {
    @Mock
    private MathService mathServiceStub;
    @InjectMocks
    private Calculator calculator;
    @Test
    public void testAdditionWithStub() {
        //Arrange
        when(mathServiceStub.add(3, 5)).thenReturn(8);
        //Act
        int result = calculator.add(3, 5);
        //Assert
        assertEquals(8, result, "3 + 5 should equal 8");
    }
}

class Calculator {

    private MathService mathService;

    public int add(int a, int b) {
        return mathService.add(a, b);
    }
}

interface MathService {
    int add(int a, int b);
}
