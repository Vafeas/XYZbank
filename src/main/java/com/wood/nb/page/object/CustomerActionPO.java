package com.wood.nb.page.object;

import com.wood.nb.pojos.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerActionPO {
    final String addCustomerButton = "button[ng-click='addCust()']";
    final String showCustomerButton = "button[ng-click='showCust()']";

    public AddCustomerPO pressAddCustomerButton(WebDriver driver, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(addCustomerButton)));
        driver.findElement(By.cssSelector(addCustomerButton)).click();
        return new AddCustomerPO();
    }
    public CustomerListPO pressShowCustomerButton(WebDriver driver) {
        driver.findElement(By.cssSelector(showCustomerButton)).click();
        return new CustomerListPO();
    }
    public Boolean verifyCustomerByFirstName(WebDriver driver, Customer customer){
        return driver.findElement(By.xpath("//td[text()='"+customer.getFirstName()+"']")).isDisplayed();
    }
}
