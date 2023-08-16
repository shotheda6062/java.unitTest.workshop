package com.unit.workshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDoubleSpyExample {
    @Test
    public void testWithdrawWithSpy() {
        // Arrange
        Account realAccount = new Account(100.0); // 實際 Account 物件
        //Test Double Spy (Doc)
        Account accountSpy = spy(realAccount); // 使用 Mockito 創建 Spy 物件
        // 將實際物件的部分原始行為保留，同時設定偽造行為
        doReturn(true).when(accountSpy).hasSufficientFunds(anyDouble()); // 設定偽造行為
        //SUT
        AccountService accountService = new AccountService();
        //Act
        accountService.withdraw(accountSpy, 50.0);
        // Assert
        verify(accountSpy, times(1)).withdraw(50.0); // 驗證方法是否被調用
    }
}

class Account {
    private double balance;
    public Account(double balance) {
        this.balance = balance;
    }
    public boolean hasSufficientFunds(double amount) {
        return balance >= amount;
    }
    public void withdraw(double amount) {
        // 實際的提款邏輯
        System.out.println("Withdrawing " + amount);
        balance -= amount;
    }
}

class AccountService {
    public void withdraw(Account account, double amount) {
        if (account.hasSufficientFunds(amount)) {
            account.withdraw(amount);
        }
    }
}