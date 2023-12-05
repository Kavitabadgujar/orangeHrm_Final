package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BaseClass {
    //WebDriver driver;
    @FindBy(xpath = "//input[@name='username']")  WebElement UserName;
    @FindBy(name = "password") WebElement Password;
    @FindBy(xpath = "//button[@type='submit']") WebElement loginButton;
    @FindBy(xpath = "//p[contains(@class,'forgot')]") WebElement forgotButton;
    @FindBy(xpath = "//button[@type='submit']") WebElement resetButton;
    @FindAll(@FindBy(tagName = "span"))
    List<WebElement> required;
    @FindBy(xpath = "//input[@name='username']/parent::div/following-sibling::span")
    WebElement usernameRequired;
    @FindBy(xpath = "//input[@name='password']/parent::div/following-sibling::span")
    WebElement passRequired;
    @FindBy(xpath = "//div[@role='alert']//p")
    WebElement alert;
    @FindBy(xpath = "//span[contains(@class,'userdropdown')]")
    WebElement user;
    @FindBy(xpath = "//a[contains(@href,'logout')]")
    WebElement logOut;
    public LoginPage(WebDriver driver){
        super(driver);
       // this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void login(String username , String password){
        try {
            UserName.click();
            UserName.sendKeys(username);
            Password.sendKeys(password);
            loginButton.click();
        }
        catch (NoSuchElementException e){
            user.click();
            logOut.click();
            UserName.click();
            UserName.sendKeys(username);
            Password.sendKeys(password);
            loginButton.click();
        }
    }
    public String errorMessage() {

        ArrayList<String> error = new ArrayList<>();
        if(!required.isEmpty()) {

           try {
               if (usernameRequired.isDisplayed()) {
                   error.add("Username "+usernameRequired.getText());
               }
           }
           catch (Exception NoSuchElementException) {
               if (passRequired.isDisplayed()) {
                   error.add("Password "+passRequired.getText());
               }
           }
        }
       return arrayListToString(error);

    }
    public String alertMessage() {
            String Alert ;
            Alert = alert.getText();
            return Alert;
    }
    public void resetPassword (String username){
            forgotButton.click();
            UserName.sendKeys(username);
            resetButton.click();
    }
}

