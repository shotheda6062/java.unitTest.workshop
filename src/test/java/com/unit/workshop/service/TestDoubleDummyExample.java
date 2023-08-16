package com.unit.workshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDoubleDummyExample {
    @Test
    public void testProcessOrder() {
        // Arrange

        // SUT 待測物件
        OrderProcessor orderProcessor = new OrderProcessor();

        // DOC Test Double - Dummy
        Customer dummyCustomer = mock(Customer.class); // 使用 Mockito 創建 Dummy 物件
        when(dummyCustomer.getName()).thenReturn("Dummy Customer"); // 設定假物件的行為

        //Act
        boolean result = orderProcessor.processOrder(dummyCustomer);

        //Assert
        assertTrue(result, "Order should be processed successfully");
    }

}
class Customer {
    public String getName() {
        // 實際的 Customer 類別方法
        return "Real Customer";
    }
}

class OrderProcessor {
    public boolean processOrder(Customer customer) {
        // 處理訂單的邏輯，這裡只是示例
        String customerName = customer.getName();
        System.out.println("Processing order for customer: " + customerName);
        return true;
    }
}