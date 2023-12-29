package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminMenu extends BaseClass {
    //gitWebDriver driver;
    @FindBy(xpath = "//li[contains(@class,'body-nav-tab')][1]")    ////span[text()='User Management ']
    WebElement UserManagement;
    @FindBy(xpath = "//li[contains(@class,'body-nav-tab')][1]/ul")
    WebElement usersPage;
    @FindBy(xpath = "//span[text()='Job ']")WebElement Job;
    @FindBy(xpath = "//span[text()='Organization ']") WebElement Organization;
    public AdminMenu(WebDriver driver){
        super(driver);
       // this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void userManagementPage(){
        try {
            UserManagement.click();
            usersPage.click();
        }catch (StaleElementReferenceException e){
            UserManagement.click();
            usersPage.click();
        }
    }
    public void jobPage(){
        Job.click();
    }
    public void organizationPage(){
        Organization.click();
    }
}
