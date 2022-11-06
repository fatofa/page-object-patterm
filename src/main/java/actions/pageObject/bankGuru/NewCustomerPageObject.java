package actions.pageObject.bankGuru;

import actions.commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import interfaces.pageUI.bankGuru.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage {
	WebDriver driver;

	//Consstructor: Ham khoi tao/ ham dung
	// - Sẽ được gọi đầu tiên khi class này được khởi tạo
	// - Hàm khởi tạo không có giá trị trả về
	// - Hàm khở tạo bắt buộc trùng tên class
	// - 1 class có thể có nhiều hàm khởi tạo
	// - Nhiều hàm khởi tạo: Khác nhau bở số lượng tham số hoặc kiểu dữ liệu
	// -> Tính đã hình
	// 1 hàm có nhiều hình thái khác nhau
	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToNameTextbox(String name) {
		waitToElementVisible(driver, NewCustomerPageUI.NAME_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.NAME_TEXTBOX, name);
		
	}

	public void inputToDateOfBirthTextbox(String dateOfBirth) {
		waitToElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH);
		sendkeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH, dateOfBirth);
		
	}

	public void inputToAddressTextbox(String address) {
		waitToElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTARE);
		sendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTARE, address);
		
	}

	public void inputToCityTextbox(String city) {
		waitToElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, city);	
	}

	public void inputToStateTextbox(String state) {
		waitToElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, state);
		
	}

	public void inputToPhoneTextbox(String phone) {
		waitToElementVisible(driver, NewCustomerPageUI.PHONE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PHONE_TEXTBOX, phone);
		
	}

	public void inputToPinTextbox(String pin) {
		waitToElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, pin);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputToEmailTextbox(String email) {
		waitToElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, email);
		
	}

	public void clickToSubmitButton() throws InterruptedException {
		waitToElementClickable(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		Thread.sleep(5000);
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, NewCustomerPageUI.NEW_SUCESS_MASSAGE);
		return getElementText(driver, NewCustomerPageUI.NEW_SUCESS_MASSAGE);
	}







}
