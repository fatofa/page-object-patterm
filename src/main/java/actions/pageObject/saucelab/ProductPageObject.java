package actions.pageObject.saucelab;

import actions.commons.AbstractPage;
import interfaces.pageUI.saucelab.ProductPageUI;
import org.openqa.selenium.WebDriver;

public class ProductPageObject extends AbstractPage {

    WebDriver driver;
    public ProductPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void selectValueInSortDropdown(String valueOfItem) {
        waitToElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN, valueOfItem);
    }

    public boolean areProductNameSortAscending() {
        return isDataStringSortedAscending(driver, ProductPageUI.PRODUCT_NAME);
    }

    public boolean areProductNameSortDescending() {
        return isDataStringSortedDescending(driver, ProductPageUI.PRODUCT_NAME);
    }

    public boolean areProductPriceSortAscending() {
        return isDataFloatSortedAscending(driver, ProductPageUI.PRODUCT_PRICE);
    }

    public boolean areProductPriceSortDescending() {
        return isDataFloatSortedDescending(driver, ProductPageUI.PRODUCT_PRICE);
    }
}
