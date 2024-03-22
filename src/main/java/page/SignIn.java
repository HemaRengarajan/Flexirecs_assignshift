package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

	
	 WebDriver driver;

	    public SignIn(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    @FindBy(className = "flex-sign-up-text")
	    public WebElement signUpLink;

	    @FindBy(name="username")
	    public WebElement userNameTxt;
	    @FindBy(name="password")
	    public WebElement passwordTxt;
	    @FindBy(xpath = "//*[contains(text(),'is required') or contains(text(),'password not valid')]")
	    public List<WebElement> errMsg;
	    @FindBy(className = "send-btn")
	    public WebElement signInBtn;
}
