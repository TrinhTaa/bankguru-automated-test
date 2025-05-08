package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.EditAccountPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.PageGeneratorManager;

public class Topic_05_EditAccount extends BaseTest{
	WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private String account_id = "144677";
	private HomePO homepage;
	private LoginPage loginpage;
	private DashBoardPage dashboard;
	private EditAccountPage editAccountPage;
	
	@BeforeSuite
	public void beforeclass() {
		driver = getBrowserDriver(url);
		homepage = PageGeneratorManager.getHomePage(driver);
		homepage.clickToBankProject(driver);
		loginpage =  PageGeneratorManager.getLoginPage(driver);
		loginpage.InputID(userid);
		loginpage.InputPW(pass);
		loginpage.clickToLoginButton(driver);
		dashboard = PageGeneratorManager.getDashBoardPage(driver);
		dashboard.clickToMenuByText("Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		boolean status = editAccountPage.verifyElementVisible();
		verifyTrue(status=true);
	}
	
	@Test
	public void EA01_Account_number_cannot_empty() {
		log.info("Account number cannot be empty");
		editAccountPage.inputTextToAccountNumber("");
		editAccountPage.sendTabToAccountNumber();
		String message = editAccountPage.getWarningMessage();
		verifyEquals(message, "Account Number must not be blank");
	}
	
	@Test
	public void EA02_Account_number_must_be_numberic() {
		log.info("Account number must be numberic");
		String data[] = {"abc1234", "1234abc"};
		for(int i=0; i <data.length; i++) {
			editAccountPage.inputTextToAccountNumber(data[i]);
			String message = editAccountPage.getWarningMessage();
			verifyEquals(message, "Characters are not allowed");
		}
	}
	
	@Test
	public void EA03_Account_cannot_have_special_character() {
		log.info("Account number cannot have special character");
		String data[] = {"123!@#", "!@#"};
		for(int i=0; i<data.length;i++) {
			editAccountPage.inputTextToAccountNumber(data[i]);
			String message = editAccountPage.getWarningMessage();
			verifyEquals(message, "Special character are not allowed");
		}
	}
	
	@Test
	public void EA06_valid_account_number() {
		log.info("Valid account number");
		editAccountPage.inputTextToAccountNumber(account_id);
		editAccountPage.clickSubmitButton();
//		Boolean status = editAccountPage.verifyElementVisible();
//		verifyFalse(status=false);
	}
	
	@Test
	public void EA04_account_number_cannot_have_blank_space() {
		log.info("Account number cannot have blank space");
		editAccountPage.inputTextToAccountNumber("123 123");
		String message = editAccountPage.getWarningMessage();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void EA05_first_character_cannot_be_space() {
		log.info("First character cannot be space");
		editAccountPage.inputTextToAccountNumber(" ");
		String message = editAccountPage.getWarningMessage();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@AfterSuite
	public void afterclass() {
		log.info("Clear test environment");
		driver.quit();
	}
}
