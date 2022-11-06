package interfaces.pageUI.bankGuru;

public class AbstractPageUI {
	public static final String LOGOUT_LINK = "//a[text()='Log out']";
	public static final String SCRIPT_LOGOUT_LINK = "document.getElementById('tabButton').click()";
	public static final String DEPOSIT_PAGE_LINK = "//a[text()='Deposit']";
	public static final String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
	public static final String FUN_TRANSFER_LINK = "//a[normalize-space()='Fund Transfer']";
	public static final String EDIT_CUSTOMER_LINK = "//a[contains(text(),'Edit Customer')]";
	
	
	//Dynamic locator (Menu page)
	public static final String DYNAMIC_MENU_PAGE_LINK_BY_TEXT = "//a[text()='%s']";

	// Pattern Object
	public static final String DYNAMIC_TEXT_BOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_TEXT_AREA_BY_NAME = "//textarea[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_VALUE = "//input[@value='%s']";
	public static final String DYNAMIC_BUTTON_BY_VALUE = "//input[@value='%s']";
	public static final String DYNAMIC_PAGE_HEADER_BY_TEXT = "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_ROW_VALUE_BY_ROW_NAME = "//td[text()='%s']/following-sibling::td";
}
