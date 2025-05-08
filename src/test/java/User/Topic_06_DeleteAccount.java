package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.DeleteAccountPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.PageGeneratorManager;

public class Topic_06_DeleteAccount extends BaseTest{
	WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private HomePO homepage;
	private LoginPage loginpage;
	private DashBoardPage dashboard;
	private DeleteAccountPage deleteaccount;
	
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
		dashboard.clickToMenuByText("Delete Account");
		deleteaccount = PageGeneratorManager.getDeleteAccountPage(driver);
	}
	
	@Test
	public void DA01_Account_number_cannot_be_empty()	{
		log.info("Account number cnnot be empty");
		deleteaccount.inputTextToAccountNumber("");
		deleteaccount.SendTabToAccountNo();
		String message = deleteaccount.getWarningMessage();
		verifyEquals(message, "Account Number must not be blank");
	}
	
	@Test
	public void DA02_Account_number_must_be_numberic() {
		log.info("Account number must be numberic");
		deleteaccount.inputTextToAccountNumber("abc123");
		String message = deleteaccount.getWarningMessage();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void DA03_Account_number_cannot_have_special_character(){
		log.info("Account number cannot have special character");
		deleteaccount.inputTextToAccountNumber("123!@#");
		String message = deleteaccount.getWarningMessage();
		verifyEquals(message, "Special characters are not allowed");
	}
	
	@Test
	public void DA04_Account_number_cannot_have_blank_space() {
		log.info("Account numbber cannot have lank space");
		deleteaccount.inputTextToAccountNumber("123 123");
		String message = deleteaccount.getWarningMessage();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void DA05_First_character_cannot_be_space() {
		log.info("First character cannot be space");
		deleteaccount.inputTextToAccountNumber(" ");
		deleteaccount.SendTabToAccountNo();
		String message = deleteaccount.getWarningMessage();
		verifyEquals(message, "Characters are not allowed");
	}
	
	@AfterSuite
	public void afterclass() {
		log.info("clear test envronment");
		driver.quit();
	}

}
