package pageClasses;

import baseClasses.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class AdminUserManagement extends BaseClass {
    //WebDriver driver;
    @FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
    WebElement addButton;
    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement password;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPassword;
    @FindBy(xpath = "//div[@class='oxd-form-row']//input[contains(@class,'oxd-input')]")
    WebElement UserName;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement UserRoll;
    @FindAll(@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]/parent::div/following-sibling::div/div"))
    List<WebElement> rollList;
    @FindBy(xpath = "//input[contains(@placeholder,'Type')]") WebElement nameHint;
    @FindAll(@FindBy(xpath = "//div[@role='option']")) List<WebElement> nameList;
    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]") WebElement status;
    @FindAll(@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]/parent::div/following-sibling::div/div"))
    List<WebElement> statusList;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//button[@type='button']")
    WebElement resetButton;
    @FindBy(xpath = "//i[contains(@class,'trash')]")
    WebElement deleteButton;
    @FindBy(xpath = "//*[text()=' Yes, Delete ']")
    WebElement delete;
    @FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]/span")
    WebElement recordsHeader;
    @FindBy(xpath = "//p[contains(@class,'title')]")
    WebElement toastTittle;
    @FindBy(xpath = "//p[contains(@class,'toast-message')]")
    WebElement toastMessage;
    @FindBy(xpath = "//span[contains(@class,'error-message')]")
    WebElement errorMsg;
    public AdminUserManagement(WebDriver driver){
        super(driver);
        //this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    private static final Logger log = LogManager.getLogger(AdminUserManagement.class.getName());
    String records;
    public void getByUserName(String user) {
        UserName.click();
        UserName.sendKeys(user);
        log.info(user+" userName is entered");
    }
    public void getByUserRoll(String userRoll){
        UserRoll.click();
        log.info("clicked on userRoll for "+userRoll);
        try {
            waitTillVisible(rollList, 2000);
            log.info("user roll list after wait for: " + userRoll);
        }
        catch (StaleElementReferenceException e) {
            UserRoll.click();
            waitTillVisible(rollList, 2000);
            log.info("user roll list after wait for: " + userRoll+ "in catch block");
        }
        for (WebElement option : rollList) {
            if (Objects.equals(option.getText(), userRoll)) {
                    String rollOption = option.getText();
                    option.click();
                    log.info("clicked on "+rollOption);
                    if(Objects.equals(UserRoll.getText(), rollOption)) {
                        log.info(rollOption + " is selected");
                        break;
                    }else {
                        option.click();
                        log.info(rollOption + " is selected in else block");
                    }
            }
        }
/*
        Actions actions = new Actions(driver);
        String roll = userRoll.toLowerCase();
        //actions.moveToElement(UserRoll).click().perform();
        switch (roll) {
            case "admin" -> {
                actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
            }
            case "ess" ->
                    actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        }
        log.info(roll+" is selected");*/
    }
    public String getByEmployeeName(String user)  {
        nameHint.click();
        String[] name = user.split(" ");
        nameHint.sendKeys(name[0]);
        String initValue = nameList.get(0).getText();
        log.info(initValue);
        wait_listReload(initValue, nameList);
        log.info(nameList.get(0).getText()+" after wait in getByEmployeeName("+user+")");
        int i= 0;
        for (WebElement emp : nameList) {
                if (Objects.equals(emp.getText(), user)) {
                    String empName = emp.getText();
                    emp.click();
                    i++;
                    log.info(empName+ " clicked");
                    break;
                }
        }
        if(i==0){
            log.info("employee not available");
            return "employee not available";
        }
        return "Employee clicked";
    }
    public void getByStatus(String Status){
        wait_isClickable(status);
        log.info("after wait_isClickable(status)");
        status.click();
        log.info("clicked on status");
        Actions actions = new Actions(driver);
       // String stts = Status.toLowerCase();
        waitTillVisible(statusList, 2000);
        log.info("status list after wait for "+Status);
        for (WebElement option : statusList) {
                log.info(option.getText());
                if (Objects.equals(option.getText(), Status)) {
                    String sttsOption = option.getText();
                    option.click();
                    if(Objects.equals(status.getText(), sttsOption)) {
                        log.info(sttsOption + " is selected");
                        break;
                    }else {
                        option.click();
                        log.info(sttsOption + " is selected in else block");
                        break;
                    }
                }
        }
       /* switch(stts){
            case "enabled" ->
                    actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
            case "disabled" ->
                    actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        }
        log.info(stts+" is selected");*/
    }
    public String searchUser(){
        records = recordsHeader.getText();
        log.info(records +" before search button is clicked");
        wait_isClickable(submitButton);
        log.info("after wait_isClickable(searchButton)");
        submitButton.click();
        log.info("search button is clicked");
        try {
            wait_search(records, recordsHeader);
            records = recordsHeader.getText();
            log.info(records + " after search button is clicked");
            return records;
        }catch (Exception e){
            if(errorMsg.isEnabled()) {
                log.info("employee "+errorMsg.getText());
                return "employee "+errorMsg.getText();
            }
            else return e.toString();
        }
    }
    public void reset(){
        resetButton.click();
        log.info("reset button is clicked");
    }
    public String delete(String name, String userName) throws InterruptedException {
        /* if user found - delete it
         * if user not found - add user - if employee not found - add employee
         *  then delete */
        records = recordsHeader.getText();
        log.info("delete( "+userName+ " )");
        getByUserName(userName);
        searchUser();
        waitToRefresh();
        log.info("username records after refresh: "+ records);
        records = recordsHeader.getText();
        if(!records.matches(".*\\d.*")) {
            addButton.click();                          // if user not found - add user
            log.info("add button clicked to add user: "+ userName);
            try {
                wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                log.info("after pageRedirect " + driver.getCurrentUrl());
            }
            catch (TimeoutException e){
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                log.info("navigate to saveSystemUsers page in catch block");
            }
            String msg= addUser(name, userName);
            log.info("user added: "+ userName+" "+ msg);
            log.info("before wait_pageRedirect "+driver.getCurrentUrl());
            try {
                wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
                log.info("after wait_pageRedirect " + driver.getCurrentUrl());
            }catch (TimeoutException te){
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
                log.info("navigate to viewSystemUsers page in catch block");
            }
            waitToRefresh();
            log.info("redirected to user management- user page");
            getByUserName(userName);
            log.info(userName+" UserName entered");
            searchUser();
        }
        scrollIntoView(deleteButton);
        deleteButton.click();
        log.info("delete button clicked for "+ userName);
        try {
            delete.click();
            log.info("confirmed delete: "+ userName);
        }//sometimes user can not be deleted
        catch (NoSuchElementException ne){
            log.info(toastMessage.getText()+" in delete()");
            return toastTittle.getText()+ " " + toastMessage.getText();
        }
        return toastTittle.getText()+ " " + toastMessage.getText();
    }
    MainMenu mainMenu = new MainMenu(driver);
    AdminMenu adminMenu = new AdminMenu(driver);
    PimModule pim = new PimModule(driver);
    public void addEmployee(String empName) throws InterruptedException {
        log.info("addEmployee() "+empName);
        mainMenu.getPimPage();
        log.info("go to pim page");
        log.info("before pim.add_Employee()"+empName);
        String msg = pim.add_Employee(empName);
        log.info(empName+" " +msg);
        Thread.sleep(2000);
        mainMenu.getAdminPage();
        log.info("go to admin page");
        adminMenu.userManagementPage();
        log.info("go to user management page");
        try {
            wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
            log.info("navigate to viewSystemUsers page after addEmployee() " + empName);
        }catch (TimeoutException e){
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
            log.info("navigate to viewSystemUsers page after addEmployee() " + empName+ " in catch block");
        }
    }

    public String addUser(String name,String user) throws InterruptedException {
        String msg = getByEmployeeName(name);
        if(Objects.equals(msg, "employee not available")){
            addEmployee(name);
            log.info("before wait_pageRedirect "+driver.getCurrentUrl());
            try {
                wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
                log.info("after wait_pageRedirect " +"in addUser("+user+" ) "+ driver.getCurrentUrl());
            }catch (TimeoutException te){
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
                log.info("navigate to viewSystemUsers page in catch block");
            }
            waitToRefresh();
            log.info("after waitToRefresh()"+"in addUser("+user+" )  "+ driver.getCurrentUrl());
            try {
                addButton.click();
                log.info("add button clicked to add user: " + user + " after addEmployee()" + " in addUser(" + user + ")");
            }catch (Exception e){
                log.info("after addButton.click() in catch block in addUser("+user+" ) "+ driver.getCurrentUrl());
            }
            try {
                wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                log.info("after pageRedirect in addUser("+user+" ) " + driver.getCurrentUrl());
            }
            catch (TimeoutException e){
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                log.info("navigate to saveSystemUsers page in catch block of addUser("+user+" )");
            }
            getByEmployeeName(name);
        }
        getByUserRoll("ESS");
        /*try {
            if (errorMsg.isEnabled()) {//**************
                log.info(errorMsg.getText()+ " now add employee: "+name);
                addEmployee(name);
                addButton.click();
                log.info("add button clicked to add user: "+ user+ " after addEmployee()" +" in addUser("+user+")");
                try {
                    wait_PageRedirected("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                    log.info("after pageRedirect in addUser("+user+" ) " + driver.getCurrentUrl());
                }
                catch (TimeoutException e){
                    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
                    log.info("navigate to saveSystemUsers page in catch block of addUser("+user+" )");
                }
                getByEmployeeName(name);
                getByUserRoll("ESS");
            }
        }
        catch (NoSuchElementException e) {          //emp present
            log.info("employee : "+name+" is available");
        }*/
        log.info("employee name entered "+name +" in addUser("+user+")");
        log.info("user roll entered" +" in addUser("+user+")");
        getByStatus("Enabled");
        log.info("status entered" +" in addUser("+user+")");
        getByUserName(user);
        log.info("username entered "+user +" in addUser("+user+")");

        password.sendKeys("1234567a");
        log.info("password entered "+user);
        confirmPassword.sendKeys("1234567a");
        log.info("password confirmed "+ user);

        //Thread.sleep(2000);
        wait_isClickable(submitButton);
        log.info("after wait_isClickable(saveButton) "+user  +" in addUser("+user+")");
        submitButton.click();
        log.info("save button clicked "+user +" in addUser("+user+")");
        try {
            String toast_msg = toastMessage.getText();
            log.info(toast_msg);
            return toast_msg +" in addUser("+user+")";
        }catch (NoSuchElementException ne){
            String error = errorMsg.getText();
            return error +" in addUser("+user+")";
        }
    }
}


