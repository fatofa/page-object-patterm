package actions.browserFactory;

public class DriverManagerFactory {
		
	protected enum Browser {
		CHROME, FIREFOX, CHROMEHEADLESS, FIREFOXHEADLESS;
	}
	
	public static DriverManager getBrowserDriver(String browserName) {
		
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		DriverManager driverManager;
		
		switch (browser) {
		case CHROME:
			System.out.println("driver chrome");
			driverManager = new ChromeDriverManager();
			System.out.println(driverManager);
			break;
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		case CHROMEHEADLESS:
			driverManager = new ChromeHeadlessDriverManager();
			break;
		case FIREFOXHEADLESS:
			driverManager = new FirefoxHeadlessDriverManager();
			break;
		default:
			throw new RuntimeException("Please choose browser name!");
		}
		
		return driverManager;
		
	}
}
