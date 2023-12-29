package testClasses;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageClasses.LeaveList;
import pageClasses.LeaveMenu;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.time.Duration;

public class LeaveListTest extends BaseTest {
   //
   // public WebDriver driver = getDriver();
    MainMenu mainMenu;
    LeaveMenu leaveMenu ;
    LeaveList leaveList ;
    LoginPage loginPage;

    private static final Logger log = LogManager.getLogger(LeaveListTest.class.getName());

    @Test
    public void aset() throws InterruptedException {
        //driver = new ChromeDriver();
       //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        //driver.manage().window().maximize();
        driver.get(baseURL);
        try {
            test = extent.createTest("Leave list setup");
        }catch (NullPointerException e){
            getExtent();
            test = extent.createTest("Leave list setup");
        }
        log.info("BaseURL loaded");
        loginPage = new LoginPage(driver);
        mainMenu = new MainMenu(driver);
        leaveMenu = new LeaveMenu(driver);
        leaveList = new LeaveList(driver);
        loginPage.login("Admin","admin123");
        log.info("login successful");
        test.log(Status.INFO,"login successful");
    }

    @Test
    public void showLeaveList() throws InterruptedException {
        test = extent.createTest("show leave list test");
        mainMenu.getLeavePage();
        leaveMenu.getLeaveListPage();
        leaveList.setFromDate(2,"June","2020");
        leaveList.setToDate(5,"July",2021);
        leaveList.setStatus("Rejected");
        log.info("status selected");
      //  leaveList.setLeaveType("US - Matternity");
       // leaveList.getByEmployeeName("a");
        //leaveList.setSubUnit("Administration");
        //leaveList.setIncludePastEmpChkBox(true);
        leaveList.submit();
       // log.error("this is error log");
        test.log(Status.INFO, "leave list");
    }
    @Test
    public void leaveListByLeaveType() throws InterruptedException {
        test = extent.createTest("leave list by leave type");
        mainMenu.getLeavePage();
        leaveMenu.getLeaveListPage();
        leaveList.setLeaveType("US - Matternity");
       // Thread.sleep(5000);
        leaveList.submit();
        test.log(Status.INFO, "leave list");
    }
}
