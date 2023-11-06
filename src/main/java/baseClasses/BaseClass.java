package baseClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import pageClasses.AdminMenu;
import pageClasses.AdminUserManagement;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.time.Duration;
import java.util.List;

public class BaseClass {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    public BaseClass(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }
    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,-200);");
    }
    public void waitTillVisible(List<WebElement> element , int duration){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(duration));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    public void waitTillVisible(WebElement element , int duration){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
