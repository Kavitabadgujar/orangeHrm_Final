package pageClasses;

import baseClasses.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeaveMenu extends BaseClass {
    private static final Logger log = LogManager.getLogger(LeaveMenu.class.getName());
   // WebDriver driver;
    @FindBy(linkText = "Apply")
    WebElement applyPage;
    @FindBy(linkText = "My Leave")
    WebElement myLeavePage;
    @FindBy(xpath = "//span[text()='Entitlements ']")
    WebElement entitlementsPage;
    @FindBy(xpath = "//li[contains(@class,'oxd-topbar-body-nav-tab')][6]")    //(linkText = "Leave List")
    WebElement leaveListPage;
    public LeaveMenu(WebDriver driver) {
        super(driver);
        //this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void getLeaveListPage(){
        try {
            leaveListPage.click();
            log.info("clicked on leaveListPage");
        } catch (StaleElementReferenceException e) {
            leaveListPage.click();
            log.info("clicked on leaveListPage in catch block");
        }

    }

}
