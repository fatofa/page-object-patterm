package interfaces.pageUI.jQuery;

public class HomePageUI {
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_NAME = "//div[text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_PAING_BY_PAGE_NUMBER = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
    public static final String DYNAMIC_PAGE_ACTIVED_BY_PAGE_NUMBER = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String DYNAMIC_DELETE_ICON_BUY_COUNTRY_NAME = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'remove')]";
    public static final String DYNAMIC_EDIT_ICON_BUY_COUNTRY_NAME = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'edit')]";
    public static final String DYNAMIC_ALL_INFOR_AT_ROWS = "//td[@data-key='females' and text()='%s']"
            + "/following-sibling::td[@data-key='country' and text()='%s']"
            + "/following-sibling::td[@data-key='males' and text()='%s']"
            + "/following-sibling::td[@data-key='total' and text()='%s']";

    public static final String DYNAMIC_INDEX_BY_COLUMNNANE = "//td[text()='%s']/preceding-sibling::td";
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX = "//tbody[1]/tr[%s]/td[%s]/input";
}
