package pageClasses;

import baseClasses.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    @FindAll(@FindBy(xpath = "//div[contains(@class,'month-selected')]/following-sibling::ul/li")) //li[contains(@class,'option')]"))
    List<WebElement> fMonthList;
    @FindAll(@FindBy(xpath = "(//input[contains(@placeholder,'yyyy')])[2]/parent::div/following-sibling::div//div[contains(@class,'month-selected')]/following-sibling::ul/li"))
    List<WebElement> tMonthlist;
    @FindBy(xpath = "//div[contains(@class,'year-selected')]")
    WebElement fYearSelector;
    @FindAll(@FindBy(xpath = "//div[contains(@class,'year-selected')]/following-sibling::ul/li"))
    List<WebElement> fYearList;
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
    public void setFromDate(int day, String month, String year ) throws InterruptedException {
        log.info("setFromDate()");
        FDate = day +"-"+ month +"-"+ year;
        fromDate.click();
        log.info("clicked on fromDate");
        fMonthSelector.click();
        log.info("clicked on from month selector");
        scrollIntoView(fMonthSelector);
        for(WebElement element : fMonthList){
            log.info(element.getText()+" from month selector");
            if (month.equals(element.getText())) {
                log.info(element.getText());
                element.click();
                log.info("from month selected");
                Thread.sleep(1000);
                break;
            }
        }
        try {
            scrollIntoView(fYearSelector);
            fYearSelector.click();
            log.info("clicked on from year selector");
        }catch (NoSuchElementException e){
            fromDate.click();
            log.info("clicked on fromDate in catch block");
            fMonthSelector.click();
            log.info("clicked on from month selector in catch block");
            scrollIntoView(fMonthSelector);
            for(WebElement element : fMonthList){
                log.info(element.getText()+" from month selector in catch block");
                if (month.equals(element.getText())) {
                    log.info(element.getText());
                    element.click();
                    log.info("from month selected in catch block");
                    Thread.sleep(1000);
                    break;
                }
            }
            scrollIntoView(fYearSelector);
            fYearSelector.click();
            log.info("clicked on from year selector in catch block");
        }
        scrollIntoView(fYearSelector);
        waitTillVisible(fYearList,2000);
        for(WebElement element : fYearList){
            log.info(element.getText()+" from year selector");
            if (year.equals(element.getText())) {
                log.info(element.getText());
                element.click();
                log.info("from year selected");
                Thread.sleep(1000);
                break;
            }
        }
        for(WebElement element : nonWorkingDays){
            log.info(element.getText()+" from non working day selector");
            if (Integer.toString(day).equals(element.getText())) {
                log.info("It's a non working day. Please select working day");
                break;
            }
        }
        for(WebElement element : workingDays){
            log.info(element.getText()+" from working day selector");
            if (Integer.toString(day).equals(element.getText())) {
                log.info(element.getText());
                element.click();
                log.info("from date selected");
                break;
            }
        }
    }
    public void setToDate(int day, String month, int year) throws InterruptedException {
        log.info("setToDate()");
       // String [] from = FDate.split("-");
        scrollIntoView(toDate);
        toDate.clear();
        log.info("toDate is clear");
        TDate = day +"-"+ month +"-"+ year;
        if(FDate.compareTo(TDate) <0 ){
            scrollIntoView(toDate);
            toDate.click();
            log.info("to date is clicked");
            tMonthSelector.click();
            log.info("to month selector clicked");
            scrollIntoView(tMonthSelector);
            for(WebElement element : tMonthlist){

                if (month.equals(element.getText())) {
                    log.info(element.getText());
                    element.click();
                    log.info("to month is selected");
                    Thread.sleep(1000);
                    break;
                }
            }
            scrollIntoView(tYearSelector);
            tYearSelector.click();
            log.info("to year is clicked");
            //scrollIntoView(yearSelector);
            for(WebElement element : tYearList){
                if (Integer.toString(year).equals(element.getText())) {
                    log.info(element.getText());
                    element.click();
                    log.info("to year is selected");
                    Thread.sleep(1000);
                    break;
                }
            }
            for(WebElement element : tNonWorkingDays){
                if (Integer.toString(day).equals(element.getText())) {
                    log.info("It's a non working day. Please select working day");
                    break;
                }
            }
            for(WebElement element : tWorkingDays){
                if (Integer.toString(day).equals(element.getText())) {
                    log.info(element.getText());
                    element.click();
                    log.info("to date is selected");
                    break;
                }
            }
        }
        else if (FDate.compareTo(TDate) >= 0){
            Assert.fail(" To date should be after from date ");
        }
    }
    public void setStatus(String select) {
        scrollIntoView(selectedStatus);
        // Iterator <WebElement> it = statusList.iterator();
        //Iterate over list , get text, compare text and select
        if (select.equals(selectedStatus.getText())) {
            log.info(select+" selected");
        } else {
            deSelectStatus.click();
            status.click();
            for (WebElement element : statusOptions) {
                if (select.equals(element.getText())) {
                    element.click();
                    log.info(select+" selected in else block");
                    break;
                }
            }
        }
    }
    public void setLeaveType(String name){
        leaveType.click();
        log.info("clicked on leave type");
        By elementLocator = By.xpath("(//div[@class='oxd-select-text-input'])[2]/parent::div/following-sibling::div/div");
        try {
            waitTillAllListLoaded(elementLocator, 3000);
        }catch (Exception e){
            leaveType.click();
            System.out.println("Exception in setLeaveType : " + e);
        }
        for (WebElement element : leaveOptions) {
            scrollIntoView(element);
            if (name.equals(element.getText())) {
                log.info(element.getText());
                element.click();
                log.info(name+ " is clicked");
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
        log.info("submit button clicked");
        log.info("Records: {}", recordHeader.getText());
    }
    public void reset(){
        resetButton.click();
    }

}
