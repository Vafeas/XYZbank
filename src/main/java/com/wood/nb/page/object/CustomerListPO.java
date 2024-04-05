package com.wood.nb.page.object;

import com.wood.nb.pojos.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CustomerListPO {
    final String searchInput = "input[ng-model='searchCustomer']";
    final String customerFirstNameSaved = "//td[text()=";

    public void setSearchInput(WebDriver driver, WebDriverWait wait, Customer customer){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchInput)));
        driver.findElement(By.cssSelector(searchInput)).sendKeys(customer.getFirstName());
    }
    public Boolean verifyCustomerByFirstName(WebDriver driver, Customer customer){
        return driver.findElement(By.xpath("//td[text()='"+customer.getFirstName()+"']")).isDisplayed();
    }
}

