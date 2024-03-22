package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	
	 WebDriver driver;

	    public Dashboard(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    @FindBy(xpath = "//p[text()='Dashboard']")
	    public WebElement dashboardTab;
	    @FindBy(xpath = "//i[@class='flx-menu-settings fa fa-cog']")
	    public WebElement settings;
	    @FindBy(xpath = "//p[contains(text(),'Shifts')]")
	    public WebElement shifts;
	    @FindBy(xpath = "//p[text()='Logout']")
	    public WebElement logout;

}
