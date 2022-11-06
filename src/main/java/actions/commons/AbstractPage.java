package actions.commons;

import java.util.*;
import java.util.concurrent.TimeUnit;

import actions.pageObject.bankGuru.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import interfaces.pageUI.bankGuru.AbstractPageUI;

public abstract class AbstractPage {
	//Selenium API Wrapper

	private WebDriverWait wait;
	private Alert alert;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	protected final Log logger;

	protected AbstractPage() {
		logger = LogFactory.getLog(getClass());
	}

	/*
	 * Web Browser
	 */
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageCurrenPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void waitToAlertPresence(WebDriver driver) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public void accepAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void senkeyToAlert(WebDriver driver, String value) {
		alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	public String getTextInAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		// Dung cho co 2 windown
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		// Dong tat ca ngoai tru window parent
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/*
	 * Web Element
	 */

	public By byXpath(String element) {
		return By.xpath(element);
	}

	public WebElement find(WebDriver driver, String element) {
		return driver.findElement(By.xpath(element));
	}

	public List<WebElement> finds(WebDriver driver, String element) {
		return driver.findElements(By.xpath(element));
	}

	public void clickToElement(WebDriver driver, String element) {
		find(driver, element).click();
	}

	public void clickToElement(WebDriver driver, String element, String... value) {
		find(driver, castToRestParameter(element, value)).click();
	}
	
	public String castToRestParameter(String xpath, String... value) {
		xpath = String.format(xpath, (Object[])value);
		return xpath;
	}
	
	public void sendkeyToElement(WebDriver driver, String element, String value) {
		find(driver, element).clear();
		find(driver, element).sendKeys(value);
	}
	public void sendkeyToElement(WebDriver driver, String element, String value, String... values) {
		find(driver, castToRestParameter(element, values)).clear();
		find(driver, castToRestParameter(element, values)).sendKeys(value);
	}

	public void selectItemInDropdown(WebDriver driver, String element, String itemValue) {
		select = new Select(find(driver, element));
		select.selectByVisibleText(itemValue);
	}

	public String getFirstSelectItemInDropdow(WebDriver driver, String element) {
		select = new Select(find(driver, element));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String element) {
		select = new Select(find(driver, element));
		return select.isMultiple();
	}

	public String getElementAttribute(WebDriver driver, String element, String attributeName) {
		return find(driver, element).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String element) {
		return find(driver, element).getText();
	}

	public String getElementText(WebDriver driver, String element, String... values) {
		return find(driver, castToRestParameter(element,values)).getText();
	}

	public int countElementSize(WebDriver driver, String element) {
		return finds(driver, element).size();
	}
	
	public int countElementSize(WebDriver driver, String element, String values) {
		return finds(driver, castToRestParameter(element, values)).size();
	}

	public void checkToCheckbox(WebDriver driver, String element) {
		if (!find(driver, element).isSelected()) {
			find(driver, element).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String element) {

		if (find(driver, element).isSelected()) {
			find(driver, element).click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String element) {
		// check element co hien thi hay khong
		return find(driver, element).isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String element, String... values) {
		// check element co hien thi hay khong
		return find(driver, castToRestParameter(element, values)).isDisplayed();
	}

	public boolean isControlUndisplayed(WebDriver driver, String element) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		System.out.println("Start time = " + new Date().toString());
		List<WebElement> elements = finds(driver, element);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if(elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible / displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible / display");
			return false;
		}

	}
	
	public boolean isControlEnabled(WebDriver driver, String element) {
		// check element co thao tac duoc hay khong
		return find(driver, element).isEnabled();
	}

	public boolean isControlSelected(WebDriver driver, String element) {
		// check element dropdown, checkbox, radiobutton co duoc chon hay chua
		return find(driver, element).isSelected();
	}
	
	public void switchToFrame(WebDriver driver, String element) {
		driver.switchTo().frame(find(driver, element));
	}

	public void switchToDefaultPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String element) {
		action = new Actions(driver);
		action.doubleClick(find(driver, element)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String element) {
		action = new Actions(driver);
		action.contextClick(find(driver, element)).perform();
	}
	
	public void hoverClickToElement(WebDriver driver, String element) {
		action = new Actions(driver);
		action.moveToElement(find(driver, element)).perform();
	}
	
	public void dragAndDropToElement(WebDriver driver, String startElement, String endElement) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, startElement), find(driver, endElement)).perform();
	}
	
	public void senKeyBordToElement(WebDriver driver, String element, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, element), key).perform();
	}
	
	public void waitToElementPresence(WebDriver driver, String element) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(byXpath(element)));
	}
	
	public void waitToElementVisible(WebDriver driver, String element) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(element)));
	}
	public void waitToElementVisible(WebDriver driver, String element, String... values) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToRestParameter(element, values))));
	}
	
	public void waitToElementInVisible(WebDriver driver, String element) {
		try {
			wait = new WebDriverWait(driver, shortTimeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(element)));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void waitToElementInVisible(WebDriver driver, String element, String... values) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToRestParameter(element, values))));
	}
	
	public void waitToElementClickable(WebDriver driver, String element) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(byXpath(element)));
	}
	public void waitToElementClickable(WebDriver driver, String element, String... values) {
		wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(byXpath(castToRestParameter(element, values))));
	}
    
    public void clickElementByJavaScript(WebDriver driver, String element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement click = driver.findElement(By.xpath(element));
        js.executeScript("arguments[0].click();" , click);
    }
    
    public void refeshPage(WebDriver driver) {
    	driver.navigate().refresh();
    }

	public boolean isDataStringSortedAscending(WebDriver driver, String xpath) {
		// Khai báo 1 Array List
		ArrayList<String> arrayList = new ArrayList<>();

		// Tìm tất cả element matching vs điều kiện (Name/ Price/..)
		List<WebElement> elementList = finds(driver, xpath);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList );

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong code: ------------ ");
		for (String name : sortedList ) {
			System.out.println(name);
		}

		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		//System.out.println(sortedList.equals(arrayList));
		return sortedList.equals(arrayList);

	}
	public boolean isDataStringSortedDescending(WebDriver driver, String xpath) {
		// Khai báo 1 Array List
		ArrayList<String> arrayList = new ArrayList<>();

		// Tìm tất cả element matching vs điều kiện (Name/ Price/..)
		List<WebElement> elementList = finds(driver, xpath);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList );

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong code: ------------ ");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// Reverse data để sort DESC (Dùng 1 trong 2 cách bên dưới)
		Collections.reverse(sortedList);
		// Collections.sort(arrayList, Collections.reverseOrder());
		System.out.println(" ------------ Dữ liệu đã SORT DESC trong code: ------------ ");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		//System.out.println(sortedList.equals(arrayList));
		return sortedList.equals(arrayList);
	}
	public boolean isDataFloatSortedAscending(WebDriver driver, String xpath) {
		// Khai báo 1 Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching vs điều kiện (Name/ Price/..)
		List<WebElement> elementList = finds(driver, xpath);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList );

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong code: ------------ ");
		for (Float name : sortedList ) {
			System.out.println(name);
		}

		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả
		// trả về sai
		System.out.println(sortedList.equals(arrayList));
		return sortedList.equals(arrayList);
	}

	public boolean isDataFloatSortedDescending(WebDriver driver, String xpath) {
		// Khai báo 1 Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching vs điều kiện (Name/ Price/..)
		List<WebElement> elementList = finds(driver, xpath);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList );

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		// Reverse data để sort DESC (Dùng 1 trong 2 cách bên dưới)
		Collections.reverse(sortedList);
		// Collections.sort(arrayList, Collections.reverseOrder());
		System.out.println(" ------------ Dữ liệu đã SORT DESC trong code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả
		// trả về sai
		System.out.println(sortedList.equals(arrayList));
		return sortedList.equals(arrayList);
	}

	/* Open bank Guru Page */
	
	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}
	
	public DepositPageObject openDepositPage(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.DEPOSIT_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.DEPOSIT_PAGE_LINK);
		return new PageGeneratorManager().getDepositPage(driver);
	}
	
	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.EDIT_CUSTOMER_LINK);
		clickToElement(driver, AbstractPageUI.EDIT_CUSTOMER_LINK);
		return PageGeneratorManager.getEditCustomerPage(driver);
	}
	
	public FundTransferPageObject openFunTransferPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.FUN_TRANSFER_LINK);
		clickToElement(driver, AbstractPageUI.FUN_TRANSFER_LINK);
		return new PageGeneratorManager().getFundTransferPage(driver);
	}
	
	public LoginPageObject clickToLogoutLink(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.LOGOUT_LINK);
		clickToElement(driver, AbstractPageUI.LOGOUT_LINK);
		waitToAlertPresence(driver);
		accepAlert(driver);
		return new PageGeneratorManager().getLoginPage(driver);
	}
	
	public LoginPageObject clickToLogoutLinkByJS(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.LOGOUT_LINK);
		clickToElement(driver, AbstractPageUI.SCRIPT_LOGOUT_LINK);
		waitToAlertPresence(driver);
		accepAlert(driver);
		return new PageGeneratorManager().getLoginPage(driver);
	}

	/* Open Dynamic Page Menu */
	
	/* 1- Số lượng page ít (10 - 20) */
	public AbstractPage openMenuPageByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK_BY_TEXT, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK_BY_TEXT, pageName);
		
		if(pageName.equals("Edit Customer")) {
			return PageGeneratorManager.getEditCustomerPage(driver);
		} else if (pageName.equals("Fund Transfer")) {
			return PageGeneratorManager.getFundTransferPage(driver);
		} else if (pageName.equals("New Customer")) {
			return PageGeneratorManager.getCustomerPage(driver);
		} else if(pageName.equals("Deposit")) {
			return PageGeneratorManager.getDepositPage(driver);
		} else {
			throw new RuntimeException();
		}
	}
	
	/* 2- Số lượng page nhiều */
	
	public void openMenuPagesByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK_BY_TEXT, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK_BY_TEXT, pageName);
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}


	/* Pattern Object */
	//driver - nameAttribute - value
	public void inputToTextBoxByAttribute(WebDriver driver, String nameAttribute, String value) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXT_BOX_BY_NAME, nameAttribute);
		//WebDriver driver, String element, String value, String... values
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXT_BOX_BY_NAME, value, nameAttribute);
	}

	public void inputToTextAreaByAttribute(WebDriver driver, String nameAttribute, String value) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXT_AREA_BY_NAME, nameAttribute);
		//WebDriver driver, String element, String value, String... values
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXT_AREA_BY_NAME, value, nameAttribute);
	}

	public void clickToRadioButtonByValue(WebDriver driver, String radioValue) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
		clickToElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
	}

	public void clickToButtonByValue(WebDriver driver, String buttonValue) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
	}

	public boolean isPageHeaderDisabledByText(WebDriver driver, String headerTextValue) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_HEADER_BY_TEXT, headerTextValue);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_HEADER_BY_TEXT, headerTextValue);
	}

	public String getRowValueByRowName(WebDriver driver, String rowName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_BY_ROW_NAME, rowName);
		return getElementText(driver,AbstractPageUI.DYNAMIC_ROW_VALUE_BY_ROW_NAME, rowName);
	}

}
