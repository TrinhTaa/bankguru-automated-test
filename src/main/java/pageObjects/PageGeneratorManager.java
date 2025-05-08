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

    public static EditCustomer getEditCustomerPage(WebDriver driver) {
        return new EditCustomer(driver);
    }

    public static DeleteCustomer getDeletePage(WebDriver driver) {
        return new DeleteCustomer(driver);
    }

	public static NewAccountPage getNewAccountPage(WebDriver driver) {
		return new NewAccountPage(driver);
	}

	public static EditAccountPage getEditAccountPage(WebDriver driver) {
		return new EditAccountPage(driver);
	}

	public static DeleteAccountPage getDeleteAccountPage(WebDriver driver) {
		return new DeleteAccountPage(driver);
	}

	public static MiniStatement getMiniStatementPage(WebDriver driver) {
		return new MiniStatement(driver);
	}

	public static CustomizeStatement getCustomizePage(WebDriver driver) {
		return new CustomizeStatement(driver);
	}

	
}
