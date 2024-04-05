package com.wood.nb.page.object;

import com.wood.nb.pojos.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPO {

    final String firstNameInput = "//label[text()='First Name :']/following-sibling::input";
    final String lastNameInput = "//label[text()='Last Name :']/following-sibling::input";
    final String postCodeInput = "//label[text()='Post Code :']/following-sibling::input";
    final String addCustomerButton = "//button[text()='Add Customer']";
    final String showCustomerButton = "button[ng-click='showCust()']";

    public void inputFirstName(WebDriver driver, WebDriverWait wait, Customer customer){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameInput)));
        driver.findElement(By.xpath(firstNameInput)).sendKeys(customer.getFirstName());
    }
    public void inputLastName(WebDriver driver, WebDriverWait wait, Customer customer){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lastNameInput)));
        driver.findElement(By.xpath(lastNameInput)).sendKeys(customer.getLastName());
    }
    public void InputPostCode(WebDriver driver, WebDriverWait wait, Customer customer){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(postCodeInput)));
        driver.findElement(By.xpath(postCodeInput)).sendKeys(customer.getPostCode());
    }
    public void pressAddCustomerButton(WebDriver driver){
        driver.findElement(By.xpath(addCustomerButton)).click();
        driver.switchTo().alert().accept();
    }
}
