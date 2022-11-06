package actions.pageObject.bankGuru;

import actions.commons.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import interfaces.pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	//Consstructor: Ham khoi tao/ ham dung
	// - Sẽ được gọi đầu tiên khi class này được khởi tạo
	// - Hàm khởi tạo không có giá trị trả về
	// - Hàm khở tạo bắt buộc trùng tên class
	// - 1 class có thể có nhiều hàm khởi tạo
	// - Nhiều hàm khởi tạo: Khác nhau bở số lượng tham số hoặc kiểu dữ liệu
	// -> Tính đã hình
	// 1 hàm có nhiều hình thái khác nhau
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}


	@Step("Get Login Pager UI")
	public String getLoginPageUrl() {
		return getPageCurrenPageUrl(driver);
	}

	@Step("Click to Here Link")
	public RegisterPageObject clickToHereLink() {
		waitToElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
//		refeshPage(driver);
//		waitToElementClickable(driver, LoginPageUI.HERE_LINK);
//		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
		
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitToElementVisible(driver, LoginPageUI.USER_ID_TEXT_BOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXT_BOX, userIDValue);
		
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, passwordValue);
		
	}

	public MainPageObject clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getMainPage(driver);	
	}

	public boolean isLoginFormDisplayed() {
//		waitToElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}

}
