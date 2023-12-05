package baseClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    /*public void scrollDown(){
        js.executeScript("window.scrollBy(0,200);");
    }*/
    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,-200);");
    }
    public void waitTillVisible(List<WebElement> list , int duration){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(duration));
        wait.until(ExpectedConditions.visibilityOfAllElements(list));


    }
    public void waitTillAllListLoaded(By element, int millis){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(millis));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(element,2));
    }
    public void waitTillVisible(WebElement element , int duration){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofMillis(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
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

        // Convert StringBuilder to String
        return stringBuilder.toString();
    }
}
