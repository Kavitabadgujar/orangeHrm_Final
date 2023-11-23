package testClasses;

import baseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageClasses.LeaveList;
import pageClasses.LeaveMenu;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.time.Duration;

public class LeaveListTest extends BaseTest {
    public WebDriver driver = getDriver();
    MainMenu mainMenu;
    LeaveMenu leaveMenu ;
    LeaveList leaveList ;
    LoginPage loginPage;
    @BeforeTest
    public void set() throws InterruptedException {
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
        mainMenu = new MainMenu(driver);
        leaveMenu = new LeaveMenu(driver);
        leaveList = new LeaveList(driver);
        loginPage.login("Admin","admin123");
    }

    @Test
    public void showLeaveList() throws InterruptedException {
        mainMenu.getLeavePage();
        leaveMenu.getLeaveListPage();
        leaveList.setFromDate(2,"June","2020");
        leaveList.setToDate(5,"July",2021);
        leaveList.setStatus("Rejected");
      //  leaveList.setLeaveType("US - Matternity");
       // leaveList.getByEmployeeName("a");
        //leaveList.setSubUnit("Administration");
        //leaveList.setIncludePastEmpChkBox(true);
        leaveList.submit();

    }
    @Test
    public void leaveListByLeaveType() throws InterruptedException {
        mainMenu.getLeavePage();
        leaveMenu.getLeaveListPage();
        leaveList.setLeaveType("US - Matternity");
       // Thread.sleep(5000);
        leaveList.submit();
    }
}
