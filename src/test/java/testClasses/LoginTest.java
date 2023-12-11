package testClasses;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageClasses.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    public WebDriver driver = getDriver();
    LoginPage loginPage;


    @BeforeClass
    public void setLog(){
        test = extent.createTest("setup");
      //  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
       // driver.manage().window().maximize();
        driver.get(baseURL);
        test.log(Status.INFO,"Base page loaded");
        loginPage = new LoginPage(driver) ;
    }

    @Test
    public void validLoginCredentials(){
        test = extent.createTest("Login with valid Login Credentials");
        loginPage.login("Admin","admin123");
        test.log(Status.INFO,"Login Credentials are entered");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertEquals(expectedURL,currentURL ,"Login Failed");
        test.log(Status.INFO, "Login Successful");
    }
    @Test
    public void validUsernameEmptyPassword(){
        test = extent.createTest("Login with valid username and empty password");
        loginPage.login("Admin","");
        test.log(Status.INFO,"Login Credentials are entered");
        String message = loginPage.errorMessage();
        test.log(Status.INFO, message );
    }
    @Test
    public void invalidUsernameValidPassword(){
        test = extent.createTest("Login with invalid username and valid password");
        loginPage.login("dummy","admin123");
        test.log(Status.INFO,"Login Credentials are entered");
        test.log(Status.INFO,loginPage.alertMessage());
    }

}
