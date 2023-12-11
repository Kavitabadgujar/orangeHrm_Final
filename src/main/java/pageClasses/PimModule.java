package pageClasses;

import baseClasses.BaseClass;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PimModule extends BaseClass {
    @FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
    WebElement addButton;
    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstName;
    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastName;
    @FindBy(xpath = "//span[contains(@class,'oxd-switch-input')]")
    WebElement loginDetailsCheckbox;
    @FindBy(xpath = "(//div[contains(@class,'oxd-grid-2')])[2]//input[contains(@class,'oxd-input')]")
    WebElement userName;
    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement password;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    public PimModule(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addEmployee(String user) throws InterruptedException {
        addButton.click();
        firstName.sendKeys("JJ");
        lastName.sendKeys("Swift");
        scrollIntoView(loginDetailsCheckbox);
        loginDetailsCheckbox.click();
        userName.sendKeys(user);
        password.sendKeys("1234567a");
        confirmPassword.sendKeys("1234567a");

        scrollIntoView(saveButton);
        saveButton.click();
        Thread.sleep(2000);

    }
}
