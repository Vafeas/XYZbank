package com.wood.nb.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class LoginPO {
    final String manager_login_button = "//button[text()='Bank Manager Login']";
    final String customer_login_button = "//button[text()='Customer Login']";

    public CustomerActionPO pressManagerLoginButton(WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(manager_login_button)));
        driver.findElement(By.xpath(manager_login_button)).click();
        return  new CustomerActionPO();
    }
    public void pressCustomerLoginButton(WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(customer_login_button)));
        driver.findElement(By.xpath(customer_login_button)).click();
    }
}
