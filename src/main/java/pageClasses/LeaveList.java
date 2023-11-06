package pageClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class LeaveList extends BaseClass {

    @FindBy(xpath = "(//input[@placeholder='dd-mm-yyyy'])[1]")
    WebElement fromDate;
    @FindBy(xpath = "(//input[@placeholder='dd-mm-yyyy'])[2]")
    WebElement toDate;
    @FindBy(xpath = "//div[contains(@class,'month-selected')]")
    WebElement monthSelector;
    @FindAll(@FindBy(xpath = " //li[contains(@class,'option')]"))
    List<WebElement> calList;
    @FindBy(xpath = "//div[contains(@class,'year-selected')]")
    WebElement yearSelector;
    @FindAll(@FindBy(xpath = "//div[contains(@class,'non-working-day')]"))
    List<WebElement> nonWorkingDays;
    @FindAll(@FindBy(xpath = " //div[@class='oxd-calendar-date']"))
    List<WebElement> workingDays;
    @FindBy(xpath = "(//div[contains(@class,'oxd-grid-4')])[1]//div[@class='oxd-select-text-input'][1]")
    WebElement status;
    @FindAll(@FindBy(xpath = "//div[@role='option']"))
    List<WebElement> options;
    @FindBy(xpath = "//span[contains(@class,'oxd-multiselect-chips-selected')]")
    WebElement selectedStatus;
    @FindBy(xpath = "//span[contains(@class,'oxd-multiselect-chips-selected')]/i")
    WebElement deSelectStatus;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement leaveType;
    @FindBy(xpath = "//input[contains(@placeholder,'Type')]") WebElement nameHint;
    @FindAll(@FindBy(xpath = "//div[@role='listbox']")) List<WebElement> nameList;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[3]")
    WebElement subUnitSelect;
    @FindBy(xpath = "//span[contains(@class,'switch')]") WebElement includePastEmpChkBox;
    @FindBy(xpath = "//button[@type='submit']") WebElement submitButton;
    @FindBy(xpath = "//button[@type='reset']") WebElement resetButton;
    public LeaveList(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    String FDate;
    String TDate;
    public void setFromDate(int day, String month, String year ){
        FDate = day +"-"+ month +"-"+ year;
        fromDate.click();
        monthSelector.click();
        for(WebElement element : calList){
            if (month.equals(element.getText())) {
                element.click();
                break;
            }
        }
        yearSelector.click();
        for(WebElement element : calList){
            if (year.equals(element.getText())) {
                element.click();
                break;
            }
        }
        for(WebElement element : nonWorkingDays){
            if (Integer.toString(day).equals(element.getText())) {
                System.out.println("It's a non working day. Please select working day");
                break;
            }
        }
        for(WebElement element : workingDays){
            if (Integer.toString(day).equals(element.getText())) {
                element.click();
                break;
            }
        }
    }
    public void setToDate(int day, String month, int year)  {
        String [] from = FDate.split("-");
        toDate.clear();
        TDate = day +"-"+ month +"-"+ year;
        if(FDate.compareTo(TDate) <0 ){
            scrollIntoView(toDate);
            toDate.click();
            monthSelector.click();
            for(WebElement element : calList){
                if (month.equals(element.getText())) {
                    element.click();
                    break;
                }
            }
            yearSelector.click();
            for(WebElement element : calList){
                if (Integer.toString(year).equals(element.getText())) {
                    element.click();
                    break;
                }
            }
            for(WebElement element : nonWorkingDays){
                if (Integer.toString(day).equals(element.getText())) {
                    System.out.println("It's a non working day. Please select working day");
                    break;
                }
            }
            for(WebElement element : workingDays){
                if (Integer.toString(day).equals(element.getText())) {
                    element.click();
                    break;
                }
            }
        }
        else if (FDate.compareTo(TDate) >= 0){
            System.out.println(" To date should be after from date ");
        }
    }
    public void setStatus(String select) {
        scrollIntoView(selectedStatus);
        // Iterator <WebElement> it = statusList.iterator();
        //Iterate over list , get text, compare text and select
        if (select.equals(selectedStatus.getText())) {
            return;
        } else {
            deSelectStatus.click();
            status.click();
            for (WebElement element : options) {
                if (select.equals(element.getText())) {
                    element.click();
                    break;
                }
            }
        }
    }
    public void setLeaveType(String name){
        leaveType.click();
        for (WebElement element : options) {
            if (name.equals(element.getText())) {
                //System.out.println(element.getText());
                element.click();

                break;
            }
        }
    }
    public void getByEmployeeName(String name) throws InterruptedException {
        nameHint.click();
        nameHint.sendKeys(name);
        waitTillVisible(nameList , 10000);
        Thread.sleep(3000);
        nameList.get(0).click();
    }
    public void setSubUnit(String select) {
        if (select.equals(subUnitSelect.getText())) {
            return;
        } else {
            subUnitSelect.click();
            for (WebElement element : options) {
                if (select.contains(element.getText())) {
                    element.click();
                    break;
                }
            }
        }
    }
    public void setIncludePastEmpChkBox(boolean TrueFalse){

        boolean status = includePastEmpChkBox.isSelected();
        if(status != TrueFalse)
            includePastEmpChkBox.click();
    }
    public void submit(){
        submitButton.click();
    }
    public void reset(){
        resetButton.click();
    }

}
