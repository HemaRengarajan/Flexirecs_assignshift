package employer;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.Dashboard;
import page.Shifts;
import page.SignIn;

public class AssignNewShiftTest {

	WebDriver driver;
    WebDriverWait wait;
    String userName = "employertest@flexirecs.com";
    String password = "Password2";


    @BeforeTest
    public void launchBrowser1() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test(dataProvider = "jobRole")
    public void assignNewShiftTest1(String dateCondition, String jobRole, String allocatedCandidate, String businessUnit, String shiftTime, String breakTime, String condition, String errorMsg) {
        driver.get("https://temp-staging.hiry.co.uk/login");
        SignIn si = new SignIn(driver);
        Dashboard db = new Dashboard(driver);
        Shifts sh = new Shifts(driver);
        page.AssignNewShift ans = new page.AssignNewShift(driver);
        Random random = new Random();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String[] date;
        String date1, newDay, month, year;
        Select obj;
        Assert.assertTrue(si.signInBtn.isEnabled(), "SignIn Page is not loaded");
        si.userNameTxt.sendKeys(userName);
        si.passwordTxt.sendKeys(password);
        si.signInBtn.click();
        Assert.assertTrue(db.dashboardTab.isDisplayed(), "Dashboard page is not loaded");
        db.shifts.click();
        sh.assignShiftBtn.click();
        if (dateCondition.equalsIgnoreCase("AllocatedDate")) {
            date1 = ans.fromDate.getText();
            System.out.println("Allocated Date: " + date1);
            date = date1.split("-");
            int day = Integer.parseInt(date[0]);
            newDay = String.valueOf(day);
            month = date[1];
            year = date[2];
            ans.dateTxt.click();
            ans.currentYearAndCurrentMonth.get(1).click();
            for (int i = 0; i < ans.allAvailableYears.size(); i++) {
                if (ans.allAvailableYears.get(i).getText().equals(year)) {
                    ans.allAvailableYears.get(i).click();
                }
            }
            ans.currentYearAndCurrentMonth.get(0).click();
            for (int i = 0; i < ans.allAvailableMonths.size(); i++) {
                if (ans.allAvailableMonths.get(i).getText().contains(month)) {
                    ans.allAvailableMonths.get(i).click();
                }
            }
            for (int i = 0; i < ans.allAvailableDays.size(); i++) {
                if (ans.allAvailableDays.get(i).getText().equals(newDay)) {
                    ans.allAvailableDays.get(i).click();
                }
            }
            ans.breakTimeTxt.click();
        } else if (dateCondition.equalsIgnoreCase("Valid")) {
            ans.dateTxt.click();
            ans.allAvailableDays.get(random.nextInt(ans.allAvailableDays.size())).click();
            ans.nextMonthArrow.click();
            ans.allAvailableDays.get(random.nextInt(ans.allAvailableDays.size())).click();
            String selectedDates = ans.selectedDates.getAttribute("value");
            System.out.println("Shift Date: " + selectedDates);
            date = selectedDates.split(",");
            Assert.assertEquals(date.length, 2, "Selected dates missing");
            ans.breakTimeTxt.click();
        }
        if (!jobRole.isEmpty()) {
            ans.selectJobRole.click();
            for (WebElement element : ans.jobRoleAndShiftAllocated) {
                if (element.getText().equalsIgnoreCase(jobRole)) {
                    element.click();
                    break;
                }
            }
            System.out.println("Job Role: " + ans.selectedJobRole.getText());
        }
        if (!allocatedCandidate.isEmpty()) {
            ans.shiftAllocatedTo.click();
            for (WebElement element : ans.jobRoleAndShiftAllocated) {
                if (element.getText().equalsIgnoreCase(allocatedCandidate)) {
                    element.click();
                    break;
                }
            }
            System.out.println("Shift Allocated To: " + ans.selectedShiftAllocatedTo.getText());
        }
        obj = new Select(ans.selectBusinessUnit);
        obj.selectByVisibleText(businessUnit);
        obj = new Select(ans.selectShiftTime);
        obj.selectByVisibleText(shiftTime);
        ans.noBreakPaidRadioBtn.click();
        ans.breakTimeTxt.clear();
        ans.breakTimeTxt.sendKeys(breakTime);
        ans.addNowBtn.click();
        if (condition.equalsIgnoreCase("Valid")) {
           // wait.until(ExpectedConditions.visibilityOf(ans.successMsg));
          //  Assert.assertEquals(ans.successMsg.getText(), "Success", "Valid Data is not added to Shifts");
        } else {
            wait.until(ExpectedConditions.visibilityOf(ans.errorMsg));
            Assert.assertEquals(ans.errorMsg.getText(), errorMsg, "Error message mismatch");
        }
    }

    @DataProvider
    public Object[][] jobRole() {
        return new Object[][]{
                //{"dateCondition","jobrole","allocatedCanditate","BusinessUnit","ShiftTime","BreakTime","Condition","errorMsg"}
                {"Valid", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "20", "Valid", ""},
                {"Empty", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "20", "Invalid", "please complete the form"},
                {"Valid", "", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "20",  "Invalid", "please complete the form"},
                {"Valid", "Senior Carer", "", "Flexiunit", "Morning (08:00-14:00)", "20", "Invalid", "please complete the form"},
                {"Valid", "Senior Carer", "Deepak Panwar ( Northolt)", "select", "Morning (08:00-14:00)", "20", "Invalid", "please complete the form"},
                {"Valid", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", " select shift Time", "20", "Invalid", "please complete the form"},
                {"Valid", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "", "Valid", "please complete the form"},
                {"Valid", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "185", "Invalid", "Break time should be less than 180 minutes"},
                {"AllocatedDate", "Senior Carer", "Deepak Panwar ( Northolt)", "Flexiunit", "Morning (08:00-14:00)", "20", "Invalid", "You can't book this shift, You have already booked a shift on the same day or marked unavailable in the availability calendar"}
        };
    }
}
