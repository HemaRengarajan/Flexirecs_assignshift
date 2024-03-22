package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssignNewShift {
	
	WebDriver driver;

    public AssignNewShift(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
 
    @FindBy(className = "rmdp-input")
    public WebElement dateTxt;
    @FindBy(xpath = "//div[@class='rmdp-day-picker ']/div/div[@class='rmdp-week']/div[@class='rmdp-day' or @class='rmdp-day rmdp-today']")
    public List<WebElement> allAvailableDays;
    @FindBy(xpath = "//div[@class='rmdp-header-values']//span")
    public List<WebElement> currentYearAndCurrentMonth;
    @FindBy(xpath = "//div[@class='rmdp-year-picker']/div[@class='rmdp-ym']/div[not(@class='rmdp-day rmdp-disabled')]")
    public List<WebElement> allAvailableYears;
    @FindBy(xpath = "//div[@class='rmdp-month-picker']/div[@class='rmdp-ym']/div[not(@class='rmdp-day rmdp-disabled')]")
    public List<WebElement> allAvailableMonths;
    @FindBy(xpath = "//span[@class='rmdp-arrow-container rmdp-right ']")
    public WebElement nextMonthArrow;
    @FindBy(xpath = "//div/input[@class='rmdp-input']")
    public WebElement selectedDates;
    @FindBy(xpath = "//label[contains(text(),'Jobrole')]/../div //input[contains(@id,'react-select-')]")
    public WebElement selectJobRole;
    @FindBy(xpath = "//div[@role='listbox']/div")
    public List<WebElement> jobRoleAndShiftAllocated;

    @FindBy(xpath = "//div[@class=' css-1dimb5e-singleValue']")
    public WebElement selectedJobRole;
    @FindBy(xpath ="//label[contains(text(),'Shift allocated to')]/../div //input[contains(@id,'react-select-')]")
    public WebElement shiftAllocatedTo;
    @FindBy(xpath = "//div[@class=' css-9jq23d']")
    public WebElement selectedShiftAllocatedTo;
    @FindBy(xpath = "//div[@class='form-group']/select[@name='business_unit']")
    public WebElement selectBusinessUnit;
    @FindBy(xpath = "//select[@name='section_type']")
    public WebElement selectShiftTime;
    @FindBy(xpath = "//label[normalize-space()='Yes']")
    public WebElement yesBreakPaidRadioBtn;
    @FindBy(xpath = "//label[normalize-space()='No']")
    public WebElement noBreakPaidRadioBtn;
    @FindBy(name = "break_time")
    public WebElement breakTimeTxt;
    @FindBy(xpath = "//div[contains(@class,'btn-primary-blue-border')]")
    public WebElement addNowBtn;
    @FindBy(className = "color-red")
    public WebElement errorMsg;
    @FindBy(xpath = "//div[@class='alert alertboxCustomSuccess']/p[normalize-space()='Success']")
    public WebElement successMsg;
    @FindBy(xpath = "(//p[@class='username'])[1]")
    public WebElement firstCandidate;
    @FindBy(xpath = "(//tr[@class='row-body2']/td[3])[1]")
    public WebElement fromDate;

}
