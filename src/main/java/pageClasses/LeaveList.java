package pageClasses;

import baseClasses.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LeaveList extends BaseClass {
    private static final Logger log = LogManager.getLogger(LeaveList.class.getName());
    @FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[1]")
    WebElement fromDate;
    @FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]")
    WebElement toDate;
    @FindBy(xpath = "//div[contains(@class,'month-selected')]")
    WebElement fMonthSelector;
    @FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'month-selected')]")
    WebElement tMonthSelector;
    @FindAll(@FindBy(xpath = " //li[contains(@class,'option')]"))
    List<WebElement> calList;
    @FindAll(@FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'month-selected')]/following-sibling::ul/li"))
    List<WebElement> tMonthlist;
    @FindBy(xpath = "//div[contains(@class,'year-selected')]")
    WebElement fYearSelector;
    @FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'year-selected')]")
    WebElement tYearSelector;
    @FindAll(@FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'year-selected')]/following-sibling::ul/li"))
    List<WebElement> tYearList;
    @FindAll(@FindBy(xpath = "//div[contains(@class,'non-working-day')]"))
    List<WebElement> nonWorkingDays;
    @FindAll(@FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'non-working-day')]"))
    List<WebElement> tNonWorkingDays;
    @FindAll(@FindBy(xpath = " //div[@class='oxd-calendar-date']"))
    List<WebElement> workingDays;
    @FindAll(@FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[@class='oxd-calendar-date']"))
    List<WebElement> tWorkingDays;
    @FindBy(xpath = "(//div[contains(@class,'oxd-grid-4')])[1]//div[@class='oxd-select-text-input'][1]")
    WebElement status;
    @FindAll(@FindBy(xpath = "//div[@role='option']"))
    List<WebElement> options;
    @FindAll(@FindBy(xpath = "(//div[contains(@class,'oxd-grid-4')])[1]//div[@class='oxd-select-text-input'][1]/parent::div/following-sibling::div/div"))
    List<WebElement> statusOptions;
    @FindBy(xpath = "//span[contains(@class,'oxd-multiselect-chips-selected')]")
    WebElement selectedStatus;
    @FindBy(xpath = "//span[contains(@class,'oxd-multiselect-chips-selected')]/i")
    WebElement deSelectStatus;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement leaveType;
    @FindAll(@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]/parent::div/following-sibling::div/div"))
    List<WebElement> leaveOptions;
    @FindBy(xpath = "//input[contains(@placeholder,'Type')]") WebElement nameHint;
    @FindAll(@FindBy(xpath = "//div[@role='listbox']")) List<WebElement> nameList;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[3]")
    WebElement subUnitSelect;
    @FindAll(@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[3]/parent::div/following-sibling::div/div"))
    List<WebElement> subUnitOptions;
    @FindBy(xpath = "//span[contains(@class,'switch')]") WebElement includePastEmpChkBox;
    @FindBy(xpath = "//button[@type='submit']") WebElement submitButton;
    @FindBy(xpath = "//button[@type='reset']") WebElement resetButton;
    @FindBy(xpath = "//div[@class='orangehrm-header-container']/span")
    WebElement recordHeader;
    public LeaveList(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    String FDate;
    String TDate;
    public void setFromDate(int day, String month, String year ){
        FDate = day +"-"+ month +"-"+ year;
        fromDate.click();
        fMonthSelector.click();
        scrollIntoView(fMonthSelector);
        for(WebElement element : calList){
            if (month.equals(element.getText())) {
                element.click();
                break;
            }
        }
        fYearSelector.click();
        scrollIntoView(fYearSelector);
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
       // String [] from = FDate.split("-");
        scrollIntoView(toDate);
        toDate.clear();
        TDate = day +"-"+ month +"-"+ year;
        if(FDate.compareTo(TDate) <0 ){
            scrollIntoView(toDate);
            toDate.click();
            tMonthSelector.click();
            scrollIntoView(tMonthSelector);
            for(WebElement element : tMonthlist){
                if (month.equals(element.getText())) {
                    element.click();
                    break;
                }
            }
            scrollIntoView(tYearSelector);
            tYearSelector.click();
            //scrollIntoView(yearSelector);
            for(WebElement element : tYearList){
                if (Integer.toString(year).equals(element.getText())) {
                    element.click();
                    break;
                }
            }
            for(WebElement element : tNonWorkingDays){
                if (Integer.toString(day).equals(element.getText())) {
                    Assert.fail("It's a non working day. Please select working day");
                    break;
                }
            }
            for(WebElement element : tWorkingDays){
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
            for (WebElement element : statusOptions) {
                if (select.equals(element.getText())) {
                    element.click();
                    break;
                }
            }
        }
    }
    public void setLeaveType(String name){
        leaveType.click();
        By elementLocator = By.xpath("(//div[@class='oxd-select-text-input'])[2]/parent::div/following-sibling::div/div");
        waitTillAllListLoaded(elementLocator,3000);
        for (WebElement element : options) {

            scrollIntoView(element);
            if (name.equals(element.getText())) {
                System.out.println(element.getText());
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
            for (WebElement element : subUnitOptions) {
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
        log.info("Records: {}", recordHeader.getText());

    }
    public void reset(){
        resetButton.click();
    }

}
