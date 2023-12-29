package baseClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseClass {
    protected static WebDriver driver;
    protected JavascriptExecutor js;
    public BaseClass(WebDriver driver){
        BaseClass.driver = driver;
        js = (JavascriptExecutor) driver;
    }
    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,-200);");
    }
    public void waitTillVisible(List<WebElement> list , int millis){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(millis));
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }
    public void wait_listReload( String initialValue, List<WebElement> list ){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))  // Maximum wait time
                .pollingEvery(Duration.ofMillis(500))  // Polling interval
                .ignoring(Exception.class);

        wait.until(driver -> {
            System.out.println(list.get(0).getText()+ " wait_listReload() ");
            // Check if the values in the list have changed
            if (!initialValue.equals(list.get(0).getText())) {
                   return true;  // Value has changed
            }
            return false;  // Values have not changed
        });
    }
    public void wait_search(String initialValue , WebElement element){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(3000));
        wait.until(driver -> {
            String reloadValue= element.getText();
            if (!initialValue.equals(reloadValue)) {
                return true;  // Value has changed
            }
            return false;  // Values have not changed
        });
    }
    public void wait_PageRedirected(String expectedURL){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(3000));
        wait.until(ExpectedConditions.urlToBe(expectedURL));
    }
    public void waitToRefresh(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(2000));
        wait.until(webDriver -> isPageRefreshed(driver));
       // wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, initValue)));
    }
    private static boolean isPageRefreshed(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript("return document.readyState").equals("complete");
    }
    public void waitTillAllListLoaded(By element, int millis){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(millis));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(element,2));
    }
    public void waitTillVisible(WebElement element , int duration){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void wait_isClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(2000));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitStaleElement(WebElement element , int duration){
        WebDriverWait w = new WebDriverWait(driver ,Duration.ofMillis(duration));
        w.until(ExpectedConditions.stalenessOf(element));
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        // Iterate through the ArrayList and append each element to the StringBuilder
        for (String str : arrayList) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
