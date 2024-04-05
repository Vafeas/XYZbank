package com.wood.nb.business.object;

import com.wood.nb.page.object.AddCustomerPO;
import com.wood.nb.page.object.CustomerActionPO;
import com.wood.nb.page.object.LoginPO;
import com.wood.nb.pojos.Customer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerBO {
    LoginPO loginPO = new LoginPO();

    public void navigateAndCreateCustomer(WebDriver driver, WebDriverWait wait, Customer customer){
        CustomerActionPO customerActionPO = loginPO.pressManagerLoginButton(driver, wait);
        createCustomer(driver,wait,customer,customerActionPO);
    }
    public void createCustomer(WebDriver driver, WebDriverWait wait, Customer customer, CustomerActionPO customerActionPO ){
        AddCustomerPO addCustomerPO = customerActionPO.pressAddCustomerButton(driver,wait);
        addCustomer(driver,wait,customer,addCustomerPO);
    }
    public void addCustomer(WebDriver driver, WebDriverWait wait, Customer customer, AddCustomerPO addCustomerPO){
        addCustomerPO.inputFirstName(driver,wait,customer);
        addCustomerPO.inputLastName(driver,wait,customer);
        addCustomerPO.InputPostCode(driver,wait,customer);
        addCustomerPO.pressAddCustomerButton(driver);
    }
}
