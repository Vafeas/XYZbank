import com.wood.nb.business.object.CustomerBO;
import com.wood.nb.business.object.config.TestBase;
import com.wood.nb.business.object.config.WebDriverFactory;
import com.wood.nb.page.object.CustomerActionPO;
import com.wood.nb.page.object.CustomerListPO;
import com.wood.nb.pojos.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class XYZbankTest extends TestBase {

    Customer customer;
    Account account;
    Deposit deposit;
    WebDriver driver;
    WebDriverWait wait;
    CustomerActionPO customerActionPO = new CustomerActionPO();
    CustomerListPO customerListPO;
    CustomerBO customerBO = new CustomerBO();

    @BeforeClass(alwaysRun = true)
    public void initEnv(){
        driver = WebDriverFactory.getDriver();
        wait = WebDriverFactory.getWait();
    }

    @BeforeClass (alwaysRun = true)
    public void init(){
        DataLoader dataLoader = new DataLoader();

        customer = dataLoader.loadCustomer();
        account = dataLoader.loadCustomerAcccount(customer);
        deposit = dataLoader.loadDeposit();
    }

    @Test(alwaysRun = true , description = "Create a customer , verify the creation.")
    public void test_step1(){

        customerBO.navigateAndCreateCustomer(this.driver,this.wait,this.customer);

        customerListPO = customerActionPO.pressShowCustomerButton(driver);

        customerListPO.setSearchInput(this.driver, this.wait, customer);

        Assert.assertTrue(customerListPO.verifyCustomerByFirstName(driver,customer));
    }

    @Test(alwaysRun = true, description = "Assign account to customer",dependsOnMethods = "test_step1")
    public void test_step2(){
        driver.findElement(By.cssSelector("button[ng-click='openAccount()']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect")));
        new Select(driver.findElement(By.id("userSelect"))).selectByVisibleText(account.getCustomerName());
        new Select(driver.findElement(By.id("currency"))).selectByVisibleText(account.getCurrency().toString());

        driver.findElement(By.xpath("//button[text()='Process']")).click();
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-click='showCust()']")));
        driver.findElement(By.cssSelector("button[ng-click='showCust()']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[ng-model='searchCustomer']")));
        driver.findElement(By.cssSelector("input[ng-model='searchCustomer']")).sendKeys(customer.getLastName());

        String text = driver.findElement(By.xpath("//td[text()='"+customer.getLastName()+"']/following-sibling::td[2]/span")).getText();
        Assert.assertFalse(text.isBlank());
    }

    @Test(alwaysRun = true , description = "Make a deposit, verify transaction", dependsOnMethods = "test_step2")
    public void test_step3() throws InterruptedException {

        driver.findElement(By.xpath("//button[text()='Home']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Customer Login']")));
        driver.findElement(By.xpath("//button[text()='Customer Login']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect")));
        new Select(driver.findElement(By.id("userSelect"))).selectByVisibleText(customer.getFirstName()+" "+customer.getLastName());

        driver.findElement(By.xpath("//button[text()='Login']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-click='deposit()']")));
        driver.findElement(By.cssSelector("button[ng-click='deposit()']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[ng-model='amount']")));

        driver.findElement(By.cssSelector("input[ng-model='amount']")).sendKeys(deposit.getAmount());

        driver.findElement(By.xpath("//button[text()='Deposit']")).click();

        driver.findElement(By.cssSelector("button[ng-click='transactions()']")).click();

        Thread.sleep(2000);
        driver.navigate().refresh();
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='1000']")).isDisplayed());
    }

    @AfterClass
    public void close(){
        driver.close();
    }
}

