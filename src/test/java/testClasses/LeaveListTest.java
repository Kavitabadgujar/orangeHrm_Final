package testClasses;

import baseTest.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageClasses.LeaveList;
import pageClasses.LeaveMenu;
import pageClasses.MainMenu;

public class LeaveListTest extends LoginTest {
    MainMenu mainMenu;
    LeaveMenu leaveMenu ;
    LeaveList leaveList ;

    @BeforeTest
    public void set(){
        mainMenu = new MainMenu(driver);
        leaveMenu = new LeaveMenu(driver);
        leaveList = new LeaveList(driver);
    }

    @Test
    public void leaveList() throws InterruptedException {
        mainMenu.getLeavePage();
        leaveMenu.getLeaveListPage();
        leaveList.setFromDate(2,"June","2020");
        leaveList.setToDate(4,"June",2021);
        leaveList.setStatus("Rejected");
        leaveList.setLeaveType("US - Matternity");
        leaveList.getByEmployeeName("a");
        leaveList.setSubUnit("Administration");
        leaveList.setIncludePastEmpChkBox(true);
        leaveList.submit();
    }

}
