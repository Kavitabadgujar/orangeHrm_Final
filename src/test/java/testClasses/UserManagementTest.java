package testClasses;

import baseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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
      // driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
       driver.manage().window().maximize();

       loginPage = new LoginPage(driver);
      // mainMenu = new MainMenu(driver);
       //adminMenu = new AdminMenu(driver);
       //adminUserManage = new AdminUserManagement(driver);
       driver.get(baseURL);
       loginPage.login("Admin","admin123");
   }

    @Test
    public void userManagement() throws InterruptedException {
        mainMenu.getAdminPage();
        //adminMenu.userManagementPage();
        adminUserManage.getByUserName("Admin");
        adminUserManage.getByUserRoll("Admin");
        adminUserManage.getByEmployeeName("Paul");
        adminUserManage.getByStatus("Enabled");
        adminUserManage.searchUser();

    }
    @Test
    public void deleteUser() throws InterruptedException {
        mainMenu.getAdminPage();
       // adminMenu.userManagementPage();
        adminUserManage.delete("Cheeku");
    }


}
