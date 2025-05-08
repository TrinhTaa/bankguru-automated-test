package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.NewAccountPage;
import pageObjects.PageGeneratorManager;

public class Topic_04_NewAccount extends BaseTest {
	private WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private String cus_id = "42316";
	private HomePO homePage;
	private LoginPage loginpage;
	private DashBoardPage dashboard;
	private NewAccountPage newaccount;

	@BeforeSuite
	public void beforeClass() {
		driver = getBrowserDriver(url);
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("Go to Bank project");
		homePage.clickToBankProject(driver);
		
		log.info("Input account and password to login");
		loginpage = PageGeneratorManager.getLoginPage(driver);
		loginpage.InputID(userid);
		loginpage.InputPW(pass);
		loginpage.clickToLoginButton(driver);
		
		log.info("Go to New Account page");
		dashboard = PageGeneratorManager.getDashBoardPage(driver);
		dashboard.clickToMenuByText("New Account");
		newaccount = PageGeneratorManager.getNewAccountPage(driver);
		newaccount.verifyHeadingVisible();
		
	}
	
	@Test
	public void NA01_CustomerId_cannot_be_empty() {
		log.info("Customer ID cannot be empty");
		newaccount.inputDataToTextboxByName("cusid", "");
		newaccount.sendTabToTextboxByName("cusid");
		String message = newaccount.getMessageOfFieldByName("cusid");
		verifyEquals(message, "Customer ID must not blank");
		
	}
	
	@Test
	public void NA02_CustomerId_must_be_numberic() {
		log.info("Customer ID must be numberic");
		String data[]= {"1234acc", "Acc1234"};
		for(int i=0; i<data.length; i++) {
			newaccount.inputDataToTextboxByName("cusid", data[i]);
			String message = newaccount.getMessageOfFieldByName("cusid");
			verifyEquals(message, "Customer id must be numberic");
		}
		
	}
	
	@Test
	public void NA03_CustomerId_cannot_have_special_character() {
		log.info("Customer ID cannot have special character");
		String data[]= {"123!@#", "!@#"};
		for(int i=0; i<data.length;i++) {
			newaccount.inputDataToTextboxByName("cusid", data[i]);
			String message = newaccount.getMessageOfFieldByName("cusid");
			verifyEquals(message, "Customer id cannot have special character");
		}
	}
	
	@Test
	public void NA04_CustomerId_cannot_have_blank_space() {
		log.info("Customer ID cannot have blank space");
		newaccount.inputDataToTextboxByName("cusid", "123 123");
		String message = newaccount.getMessageOfFieldByName("cusid");
		verifyEquals(message, "Customer id cannot have blank space");
	}
	
	@Test
	public void NA05_CustomerId_first_character_cannot_be_space() {
		log.info("Customer id cannot have first character space");
		newaccount.inputDataToTextboxByName("cusid", " ");
		String message = newaccount.getMessageOfFieldByName("cusid");
		verifyEquals(message, "Customer id cannot have first character space");
	}
	
	@Test
	public void NA06_InitalDeposit_cannot_be_empty() {
		log.info("Initial deposit cannot be empty");
		newaccount.inputDataToTextboxByName("inideposit", "");
		String message = newaccount.getMessageOfFieldByName("inideposit");
		verifyEquals(message, "Initial deposit cannot be empty");
	}
	
	@Test
	public void NA07_InitialDeposit_must_be_numberic() {
		log.info("Initial deposit must be numberic");
		newaccount.inputDataToTextboxByName("inideposit", "deposit1213");
		String message = newaccount.getMessageOfFieldByName("inideposit");
		verifyEquals(message, "Initial Deposit must be numberic");
	}
	
	@Test
	public void NA08_InitialDeposit_cannot_have_special_character() {
		log.info("Initial Deposit cannot have special character");
		newaccount.inputDataToTextboxByName("inideposit", "1234!@#");
		String message = newaccount.getMessageOfFieldByName("inideposit");
		verifyEquals(message, "Initial Deposit cannot have special character");
	}
	
	@Test
	public void NA09_InitialDeposit_cannot_have_blank_sapce() {
		log.info("Initial Deposit cannot have blank space");
		newaccount.inputDataToTextboxByName("inideposit", "123 123");
		String message = newaccount.getMessageOfFieldByName("inideposit");
		verifyEquals(message, "Initial Deposit cannot have blank space");
	}
	
	@Test
	public void NA10_InitialDeposit_cannot_be_space() {
		log.info("Initial Deposit cannot have first character space");
		newaccount.inputDataToTextboxByName("inideposit", " ");
		String message = newaccount.getMessageOfFieldByName("inideposit");
		verifyEquals(message, "Initial Deposit cannot have first character space");			
	}
	
	@AfterSuite
	public void afterclass() {
		driver.quit();
	}

}
