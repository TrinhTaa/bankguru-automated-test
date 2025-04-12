package pageObjects;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	
	public static HomePO getHomePage(WebDriver driver) {
		return new HomePO(driver);
	}

	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}

	public static DashBoardPage getDashBoardPage(WebDriver driver) {
		return new DashBoardPage(driver);
	}

	public static NewCustomerPage getNewCusomerPage(WebDriver driver) {
		return new NewCustomerPage(driver);
	}

	
}
