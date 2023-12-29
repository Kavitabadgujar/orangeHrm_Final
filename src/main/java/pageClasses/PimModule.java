package pageClasses;

import baseClasses.BaseClass;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
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
    @FindBy(xpath = "//p[contains(@class,'toast-message')]")
    WebElement toastMessage;
    @FindBy(xpath = "//span[contains(@class,'error-message')]")
    WebElement errorMsg;

    public PimModule(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private static final Logger log = LogManager.getLogger(PimModule.class.getName());
    public String add_Employee(String user) throws InterruptedException {
        log.info("in addEmployee(): "+user);
        addButton.click();
        log.info("add button clicked to add employee");
        String[] name = user.split(" ");
        String fName = name[0];
        String lName = name[1];
        waitToRefresh();
        log.info("After refresh in add_Employee("+user+")");
        firstName.sendKeys(fName);
        log.info("first name entered: "+fName);
        lastName.sendKeys(lName);
        log.info("last name entered: "+lName);
        scrollIntoView(saveButton);
        Thread.sleep(2000);
        saveButton.click();
        log.info("save button clicked for: "+user);
        Thread.sleep(200);
        try {
            log.info(toastMessage.getText());
            return toastMessage.getText() +" "+ user;
        }catch (NoSuchElementException e){
            saveButton.click();
            log.info("save button clicked for: "+user+" in catch block");
            log.info(toastMessage.getText());
            return toastMessage.getText() +" "+ user;
        }
    }
}
