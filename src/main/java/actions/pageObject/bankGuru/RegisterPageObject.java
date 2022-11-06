package actions.pageObject.bankGuru;

import actions.commons.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import interfaces.pageUI.bankGuru.RegisterPageuUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	//Consstructor: Ham khoi tao/ ham dung
	// - Sẽ được gọi đầu tiên khi class này được khởi tạo
	// - Hàm khởi tạo không có giá trị trả về
	// - Hàm khở tạo bắt buộc trùng tên class
	// - 1 class có thể có nhiều hàm khởi tạo
	// - Nhiều hàm khởi tạo: Khác nhau bở số lượng tham số hoặc kiểu dữ liệu
	// -> Tính đã hình
	// 1 hàm có nhiều hình thái khác nhau
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Input email to textbox")
	public void inputToEmailTextbox(String string) {
		waitToElementVisible(driver, RegisterPageuUI.EMAIL_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageuUI.EMAIL_TEXT_BOX, string);
	}
	@Step("Click to button Menu")
	public void clickToSubmitButton() {
		waitToElementClickable(driver, RegisterPageuUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageuUI.SUBMIT_BUTTON);
		
	}

	public String getUserIDText() {
		waitToElementVisible(driver, RegisterPageuUI.USER_ID_TEXT);
		return getElementText(driver, RegisterPageuUI.USER_ID_TEXT);
	}

	public String getPasswordText() {
		waitToElementVisible(driver, RegisterPageuUI.PASSWORD_TEXT);
		return getElementText(driver, RegisterPageuUI.PASSWORD_TEXT);
	}

	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
		
		
	}

}
