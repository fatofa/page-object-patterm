package actions.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractPage {
	
	WebDriver driver;
	
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//Tạo ra swjj liên kết giữa FindBy vs biến WebElement
	}
	
	@FindBy(xpath = "//a[text()='here']") 
	private WebElement hereLink;
	
	@FindBy(xpath = "//input[@name='uid']") 
	private WebElement userIdTextBox;
	
	@FindBy(xpath = "//input[@name='password']") 
	private WebElement passwordTextBox;
	
	@FindBy(name = "btnLogin") 
	private WebElement loginButton;
	
	@FindBy(name = "btnReset") 
	private WebElement resetButton;
	
	@FindBy(name = "frmLogin") 
	private WebElement loginForm;
	
	
	public String getLoginPageUrl() {
		return getPageCurrenPageUrl(driver);
	}


	public void clickToHereLink() {
		waitToElementClickable(driver, hereLink);
		clickToElement(driver, hereLink);
//		refeshPage(driver);
//		waitToElementClickable(driver, hereLink);
//		clickToElement(driver, hereLink);
		
		
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitToElementVisible(driver, userIdTextBox);
		sendkeyToElement(driver, userIdTextBox, userIDValue);
		
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitToElementVisible(driver, passwordTextBox);
		sendkeyToElement(driver, passwordTextBox, passwordValue);
		
	}

	public void clickToLoginButton() {
		waitToElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}

	public boolean isLoginFormDisplayed() {
		waitToElementVisible(driver, loginForm);
		return isControlDisplayed(driver, loginForm);
	}

}
