package actions.pageObject.bankGuru;

import actions.commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import interfaces.pageUI.bankGuru.MainPageUI;


public class MainPageObject extends AbstractPage {
	WebDriver driver;

	//Consstructor: Ham khoi tao/ ham dung
	// - Sẽ được gọi đầu tiên khi class này được khởi tạo
	// - Hàm khởi tạo không có giá trị trả về
	// - Hàm khở tạo bắt buộc trùng tên class
	// - 1 class có thể có nhiều hàm khởi tạo
	// - Nhiều hàm khởi tạo: Khác nhau bở số lượng tham số hoặc kiểu dữ liệu
	// -> Tính đã hình
	// 1 hàm có nhiều hình thái khác nhau
	public MainPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getWelcomeMessageText() {
		waitToElementVisible(driver, MainPageUI.WELCOME_MESSAGE_TEXT);
		return getElementText(driver, MainPageUI.WELCOME_MESSAGE_TEXT);
	}

	public boolean isLoginFormUndisplay() {
		//waitToElementInVisible(driver, MainPageUI.LOGIN_FORM);
		return isControlUndisplayed(driver, MainPageUI.LOGIN_FORM);
	}


	
}
