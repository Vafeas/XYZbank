package com.wood.nb.business.object.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFactory {
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }
    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static WebDriverWait getWait(){
        return waitThreadLocal.get();
    }

    public static void setWait(WebDriverWait wait){
        waitThreadLocal.set(wait);
    }
}
