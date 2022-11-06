package actions.pageObject.jQuery;

import actions.commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import interfaces.pageUI.jQuery.HomePageUI;


public class HomePageObject extends AbstractPage {
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputTextToTextboxByColumnName(String columnName, String values) {
		waitToElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, columnName);
		sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, values , columnName);
		
	}

	public void navigateToPageByPageNumber(String pageNumber) {
		System.out.println("Click by page number: " + pageNumber);
		waitToElementClickable(driver, HomePageUI.DYNAMIC_PAING_BY_PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.DYNAMIC_PAING_BY_PAGE_NUMBER, pageNumber);
		
	}

	public boolean isPageActivedByPageNumber(String pageNumber) {
		System.out.println("Verify by page number: " + pageNumber);
		waitToElementVisible(driver, HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
		return isControlDisplayed(driver, HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
	}

	public void clickToDeleteIconByCountryName(String values) {
		waitToElementClickable(driver, HomePageUI.DYNAMIC_DELETE_ICON_BUY_COUNTRY_NAME, values);
		clickToElement(driver, HomePageUI.DYNAMIC_DELETE_ICON_BUY_COUNTRY_NAME, values);
		
	}

	public void clickToEditIconByCountryName(String values) {
		waitToElementClickable(driver, HomePageUI.DYNAMIC_EDIT_ICON_BUY_COUNTRY_NAME, values);
		clickToElement(driver, HomePageUI.DYNAMIC_EDIT_ICON_BUY_COUNTRY_NAME, values);
		
	}

	public boolean isAllInforDisplay(String females, String country, String males, String total) {
		waitToElementVisible(driver, HomePageUI.DYNAMIC_ALL_INFOR_AT_ROWS, females, country, males, total);
		return isControlDisplayed(driver, HomePageUI.DYNAMIC_ALL_INFOR_AT_ROWS, females, country, males, total);
	}

	public void inputTextToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String values) {
		waitToElementVisible(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMNNANE, columnName);
		int columnIndex = countElementSize(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMNNANE, columnName) + 1;
		
		waitToElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX, values, rowNumber, String.valueOf(columnIndex));
		
		
	} 
	
}
