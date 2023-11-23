package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

public class AdminUserManagement extends BaseClass {
    //WebDriver driver;
    @FindBy(xpath = "//div[@class='oxd-form-row']//input[contains(@class,'oxd-input')]")
    WebElement UserName;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement UserRoll;
    @FindBy(xpath = "//input[contains(@placeholder,'Type')]") WebElement nameHint;
    @FindAll(@FindBy(xpath = "//div[@role='listbox']")) List<WebElement> nameList;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]") WebElement status;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;
    @FindBy(xpath = "//button[@type='button']")
    WebElement resetButton;
    @FindBy(xpath = "//i[contains(@class,'trash')]")
    WebElement deleteButton;
    @FindBy(xpath = "//*[text()=' Yes, Delete ']")
    WebElement delete;
    @FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]/span")
    WebElement recordsHeader;
    @FindBy(xpath = "//p[contains(@class,'oxd-text--toast-message')]")
    WebElement infoMessage;
    public AdminUserManagement(WebDriver driver){
        super(driver);
        //this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void getByUserName(String user){
        UserName.click();
        UserName.sendKeys(user);
    }
    public void getByUserRoll(String userRoll){
        Actions actions = new Actions(driver);
        String roll = userRoll.toLowerCase();
        switch (roll) {
            case "admin" ->
                    actions.moveToElement(UserRoll).click().keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
            case "ess" ->
                    actions.moveToElement(UserRoll).click().keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        }

    }
    public void getByEmployeeName(String name) throws InterruptedException {
        nameHint.click();
        nameHint.sendKeys(name);
        waitTillVisible(nameList , 10000);
        Thread.sleep(3000);
        nameList.get(0).click();
    }
    public void getByStatus(String Status){
        status.click();
        Actions actions = new Actions(driver);
        String stts = Status.toLowerCase();
        switch(stts){
            case "enabled" ->
                    actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).click().perform();
            case "disabled" ->
                    actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).click().perform();
        }
    }
    public void searchUser(){
        searchButton.click();
    }
    public void reset(){
        resetButton.click();
    }
    public void delete(String user) throws InterruptedException {
        getByUserName( user);
        searchUser();

        String records = recordsHeader.getText();
        if( records.matches(".*\\d.*")){
           deleteButton.click();
           delete.click();

        }
        else
            System.out.println("User does not exist");

        /*Thread.sleep(3000);
        try{
            deleteButton.isDisplayed();
            deleteButton.click();
            delete.click();
        }catch (NoSuchElementException e ){
            System.out.println("element not found" + e);
           // Assert.assertFalse(deleteButton.isDisplayed(),infoMessage.getText());
        }
*/

    }


}


