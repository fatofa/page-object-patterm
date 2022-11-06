package actions.pageObject.saucelab;

import actions.commons.AbstractPage;
import interfaces.pageUI.saucelab.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToTextBoxUserName(WebDriver driver, String value) {
        waitToElementVisible(driver, LoginPageUI.USER_NAME_TEXT_BOX);
        sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXT_BOX, value);
    }

    public void inputToTextBoxPassword(WebDriver driver, String value) {
        waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXT_BOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, value);
    }

    public void clickToElementLogin(WebDriver driver) {
        waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
