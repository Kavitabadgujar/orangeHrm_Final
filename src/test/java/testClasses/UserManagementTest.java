package testClasses;

import baseTest.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageClasses.AdminMenu;
import pageClasses.AdminUserManagement;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

public class UserManagementTest extends BaseTest {
    MainMenu mainMenu;
    AdminMenu adminMenu;
   AdminUserManagement adminUserManage;
   @BeforeTest
   public void set(){
       mainMenu = new MainMenu(driver);
       adminMenu = new AdminMenu(driver);
       adminUserManage = new AdminUserManagement(driver);
   }

    @Test // (dependsOnGroups = {"login"})
    public void userManagement() throws InterruptedException {
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
        adminUserManage.getByUserName("Admin");
        adminUserManage.getByUserRoll("Admin");
        adminUserManage.getByEmployeeName("Paul");
        adminUserManage.getByStatus("Enabled");
        adminUserManage.searchUser();
        //driver.quit();
    }
    @Test
    public void deleteUser(){
        mainMenu.getAdminPage();
        adminMenu.userManagementPage();
       adminUserManage.delete("laurence.price");
    }


}
