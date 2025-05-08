package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.MiniStatement;
import pageObjects.PageGeneratorManager;

public class Topic_07_MiniStatement extends BaseTest {
	WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private HomePO homepage;
	private LoginPage loginpage;
	private DashBoardPage dashboard;
	private MiniStatement ministatement;
	
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
		dashboard.clickToMenuByText("Mini Statement");
		ministatement = PageGeneratorManager.getMiniStatementPage(driver);
	}
	
	@Test
	public void MS01_Account_number_cannot_be_empty() {
		log.info("Account number cannot be empty");
		ministatement.inputTextToAccountNumber("");
		ministatement.sendTabToAccountNumber();
		String message = ministatement.getWarningContent();
		verifyEquals(message, "Account Number must not be blank");
	}
	
	@Test
	public void MS02_Account_number_must_be_numberic() {
		log.info("Account number must be numberic");
		ministatement.inputTextToAccountNumber("abc123");
		String message = ministatement.getWarningContent();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void MS03_Account_number_cannot_have_special_character() {
		log.info("Account number cannot have special characters");
		ministatement.inputTextToAccountNumber("123!@#");
		String message = ministatement.getWarningContent();
		verifyEquals(message, "Special characters are not allowed");
	}
	
	@Test
	public void MS04_Account_number_cannot_have_blank_space() {
		log.info("Account numer cannot have blank space");
		ministatement.inputTextToAccountNumber("123 123");
		String message = ministatement.getWarningContent();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void MS05_First_character_cannot_be_space() {
		log.info("First character cannot be space");
		ministatement.inputTextToAccountNumber(" ");
		ministatement.sendTabToAccountNumber();
		String message = ministatement.getWarningContent();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@AfterSuite
	public void afterclass() {
		driver.quit();
	}

}
