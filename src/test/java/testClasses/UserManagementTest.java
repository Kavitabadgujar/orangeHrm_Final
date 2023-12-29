package testClasses;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageClasses.AdminMenu;
import pageClasses.AdminUserManagement;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UserManagementTest extends BaseTest {
   // public WebDriver driver = getDriver();
    MainMenu mainMenu ;//= new MainMenu(driver);
    AdminMenu adminMenu ;// = new AdminMenu(driver);
    AdminUserManagement adminUserManage  ;//= new AdminUserManagement(driver);
    LoginPage loginPage;
   @Test
   public void asetUp() {

      // driver = new ChromeDriver();
     //  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
       //driver.manage().window().maximize();
       try {
           test = extent.createTest("setup");
       }
       catch (NullPointerException e){
           getExtent();
           test = extent.createTest("setup");
       }
       //driver = getDriver("chrome");
       driver.get(baseURL);
       loginPage = new LoginPage(driver);
       mainMenu = new MainMenu(driver);
       adminMenu = new AdminMenu(driver);
       adminUserManage = new AdminUserManagement(driver);

       test.log(Status.INFO,"Base page loaded");
       loginPage.login("Admin","admin123");
       test.log(Status.INFO, "login successful");
   }

    @Test
    public void searchUserTest() {
        test = extent.createTest("Search User Test");
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
        adminUserManage.getByUserName("Alice.Duval");
        adminUserManage.getByUserRoll("ESS");
        adminUserManage.getByEmployeeName("Alice Duval");
        adminUserManage.getByStatus("Enabled");
        test.log(Status.INFO,"Username, UserROll, employee name, status is entered ");
        String result = adminUserManage.searchUser();
        test.log(Status.INFO, result);

    }
    @Parameters({"name","userName"})
    @Test
    public void deleteUser(String name, String userName) throws InterruptedException {
       test = extent.createTest("Delete user test");
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
        String message = adminUserManage.delete(name, userName);
        test.log(Status.INFO, message);
    }
}

