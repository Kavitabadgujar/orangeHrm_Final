package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu extends BaseClass {
    //WebDriver driver;
    @FindBy(xpath = "//a[contains(@href,'admin/viewAdminModule')]")
    WebElement Admin;
    @FindBy(xpath = "//a[contains(@href,'leave/viewLeaveModule')]") WebElement Leave;
    @FindBy(xpath = "//a[contains(@href,'viewPimModule')]")
    WebElement Pim;
    public MainMenu(WebDriver driver)  {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void getAdminPage(){
        Admin.click();
    }
    public void getLeavePage(){
        Leave.click();
    }
    public void getPimPage(){
        Pim.click();
    }
}
