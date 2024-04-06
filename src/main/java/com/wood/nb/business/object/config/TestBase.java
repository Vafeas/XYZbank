package com.wood.nb.business.object.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class TestBase {

    @BeforeClass
    @Parameters({"url","timeout.duration"})
    public void openBrowser(String url,String timeOut){
        WebDriver driver = new EdgeDriver();
        WebDriverFactory.setDriver(driver);
        driver.navigate().to(url);
        WebDriverFactory.setWait(new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeOut))));

        driver.get(url);
        driver.manage().window().maximize();
        WebDriverFactory.setDriver(driver);
        WebDriverFactory.setWait(new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeOut))));
    }
    @AfterClass(alwaysRun = true)
    public void close(){
        WebDriverFactory.getDriver().close();
    }

}
