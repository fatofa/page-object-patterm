package actions.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	private long longTimeOut = 30;
	private WebDriverWait wait;
	
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageCurrenPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void waitToElementVisible(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitToElementClickable(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public boolean isControlDisplayed(WebDriver driver, WebElement element) {
		// check element co hien thi hay khong
		return element.isDisplayed();
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void refeshPage(WebDriver driver) {
	    	driver.navigate().refresh();
	}
	

	
}


