package testClasses;

import baseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pageClasses.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    public WebDriver driver = getDriver();
    LoginPage loginPage;

    @BeforeTest
    public void setLog(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.manage().window().maximize();
        driver.get(baseURL);
        loginPage = new LoginPage(driver) ;
    }

    @Test
    public void validLoginCredentials(){
        loginPage.login("Admin","admin123");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertEquals(expectedURL,currentURL ,"Login Failed");
    }
    @Test
    public void validUsernameEmptyPassword(){
        loginPage.login("Admin","");
        loginPage.errorMessage();
        System.out.println(loginPage.errorMessage());
    }
    @Test
    public void invalidUsernameValidPassword(){
        loginPage.login("dummy","admin123");
        System.out.println(loginPage.alertMessage());
    }
}
