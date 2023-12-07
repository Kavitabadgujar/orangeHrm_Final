package testClasses;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageClasses.AdminMenu;
import pageClasses.AdminUserManagement;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.time.Duration;

public class UserManagementTest extends BaseTest {
    public WebDriver driver = getDriver();
    MainMenu mainMenu = new MainMenu(driver);
    AdminMenu adminMenu = new AdminMenu(driver);
    AdminUserManagement adminUserManage  = new AdminUserManagement(driver);
    LoginPage loginPage;
   @BeforeSuite
   public void setUp() {
       test = extent.createTest("setup");
      // driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
       driver.manage().window().maximize();

       loginPage = new LoginPage(driver);
      // mainMenu = new MainMenu(driver);
       //adminMenu = new AdminMenu(driver);
       //adminUserManage = new AdminUserManagement(driver);
       driver.get(baseURL);
       test.log(Status.INFO,"Base page loaded");
       loginPage.login("Admin","admin123");
       test.log(Status.INFO, "login successful");
   }

    @Test
    public void searchUserTest() throws InterruptedException {
        test = extent.createTest("Search User Test");
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
        adminUserManage.getByUserName("Admin");
        adminUserManage.getByUserRoll("Admin");
        adminUserManage.getByEmployeeName("Paul");
        adminUserManage.getByStatus("Enabled");
        test.log(Status.INFO,"Username, ");
        String result = adminUserManage.searchUser();
        test.log(Status.INFO, result);

    }
    @Test
    public void deleteUser() throws InterruptedException {
       test = extent.createTest("Delete user");
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
        String message = adminUserManage.delete("cccccc");
        test.log(Status.INFO, message);
    }
}

