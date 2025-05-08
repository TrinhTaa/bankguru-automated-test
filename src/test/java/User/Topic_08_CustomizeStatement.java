package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.CustomizeStatement;
import pageObjects.DashBoardPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.PageGeneratorManager;

public class Topic_08_CustomizeStatement extends BaseTest{
	WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private HomePO homepage;
	private LoginPage loginpage;
	private DashBoardPage dashboard;
	private CustomizeStatement customizepage;
	
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
		customizepage = PageGeneratorManager.getCustomizePage(driver);
	}
	
	@Test
	public void CS01_Account_number_must_not_be_blank() {
		log.info("Account number must not be blank");
		customizepage.inputTextToElementByName("accountno", "");
		customizepage.sendTabToElementByName("accountno");
		String message =  customizepage.getWarningMessageByName("accountno");
		verifyEquals(message, "Account Number must not be blank");
	}
	 @Test
	 public void CS02_Account_number_does_not_allow_characters() {
		 log.info("Account number does not allow characters");
		 customizepage.inputTextToElementByName("accountno", "abc1234");
		 String message = customizepage.getWarningMessageByName("accountno");
		 verifyEquals(message, "Characters are not allowed");
	 }
	 
	 @Test
	 public void CS03_Account_number_check_special_character() {
		 log.info("Special characters are not allowed");
		 customizepage.inputTextToElementByName("accountno", "123!@#");
		 String message = customizepage.getWarningMessageByName("accountno");
		 verifyEquals(message, "Special characters are not allowed");
	 }
	 
	 @Test
	 public void CS04_Account_no_cannot_have_blank_space() {
		 log.info("account no canot have blank space");
		 customizepage.inputTextToElementByName("accountno", "123 1234");
		 String message = customizepage.getWarningMessageByName("accountno");
		 verifyEquals(message, "Account no canot have blank space");
	 }
	 
	 @Test
	 public void CS05_Account_no_check_first_character() {
		 log.info("first character cannot be space");
		 customizepage.inputTextToElementByName("accountno", " ");
		 customizepage.sendTabToElementByName("accountno");
		 String message = customizepage.getWarningMessageByName("accountno");
		 verifyEquals(message, "Characters are not allowed");
	 }
	 
	 @AfterSuite
	 public void afterclass() {
		 driver.quit();
	 }

}
