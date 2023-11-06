package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeaveMenu extends BaseClass {
   // WebDriver driver;
    @FindBy(linkText = "Apply")
    WebElement applyPage;
    @FindBy(linkText = "My Leave")
    WebElement myLeavePage;
    @FindBy(xpath = "//span[text()='Entitlements ']")
    WebElement entitlementsPage;
    @FindBy(linkText = "Leave List")
    WebElement leaveListPage;
    public LeaveMenu(WebDriver driver) {
        super(driver);
        //this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void getLeaveListPage(){
        leaveListPage.click();
    }

}
