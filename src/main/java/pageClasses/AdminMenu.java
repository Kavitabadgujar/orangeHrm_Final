package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminMenu extends BaseClass {
    //gitWebDriver driver;
    @FindBy(xpath = "//span[text()='User Management ']")
    WebElement UserManagement;
    @FindBy(xpath = "//span[text()='Job ']")WebElement Job;
    @FindBy(xpath = "//span[text()='Organization ']") WebElement Organization;
    public AdminMenu(WebDriver driver){
        super(driver);
       // this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void userManagementPage(){
        UserManagement.click();
    }
    public void jobPage(){
        Job.click();
    }
    public void organizationPage(){
        Organization.click();
    }
}
