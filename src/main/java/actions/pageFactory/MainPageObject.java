package actions.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageObject extends AbstractPage {
	
	WebDriver driver;
	
	
	public MainPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//Tạo ra swjj liên kết giữa FindBy vs biến WebElement
	}
	
	@FindBy(xpath = "//marquee[@class='heading3']")
	private WebElement welcomMessageText;
	
	@FindBy(xpath = "//a[text()='New Customer']")
	private WebElement newCustomnerLink;
	
	public String getWelcomeMessageText() {
		waitToElementVisible(driver, welcomMessageText);
		return getElementText(driver, welcomMessageText);
	}
	public void openNewCustomerPage() {
		waitToElementClickable(driver, newCustomnerLink);
		clickToElement(driver, newCustomnerLink);
		
	}

}
