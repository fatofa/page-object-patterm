package actions.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends AbstractPage {
	
	WebDriver driver;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//Tạo ra swjj liên kết giữa FindBy vs biến WebElement
	}
	
	@FindBy(xpath = "//input[@name='emailid']")
	private WebElement emailTextBox;
	
	@FindBy(xpath = "//input[@name='btnLogin']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userIDText;

	@FindBy(xpath = "//td[text()='Password :']/following-sibling::td")
	private WebElement passwordText;

	public void inputToEmailTextbox(String string) {
		waitToElementVisible(driver, emailTextBox);
		sendkeyToElement(driver, emailTextBox, string);	
	}

	public void clickToSubmitButton() {
		waitToElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);
		
	}

	public String getUserIDText() throws InterruptedException {
//		waitToElementVisible(driver, userIDText);
		
		return getElementText(driver, userIDText);
	}

	public String getPasswordText() throws InterruptedException {
//		waitToElementVisible(driver, passwordText);
		
		return getElementText(driver, passwordText);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		
	}

}
