package User;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.NewCustomerPage;
import pageObjects.PageGeneratorManager;

public class Topic_01_NewCusomer extends BaseTest{
	WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
	private String ex_message_blank = "Customer name must not be blank";
	private String ex_message_numberic = "Numbers are not allowed";
	private String ex_message_specialCharacter = "Special characters are not allowed";
	private String ex_message_first_character ="First chracter cannot be space";
	private String city_message_blank = "City Field must be not blank";
	private String city_message_numberic = "Numbers are not allowed";
	private String city_message_special_characters = "Special characters are not allowed";
	private String state_message_blank = "State must not be blank";
	private String state_message_special_characters = "Special characters are not allowed";
	private String state_message_numberic ="Numbers are not allowed";
	private String PIN_numberic = "Characters are not allowed";
	private String PIN_blank = "PIN Code must not be blank";
	private String PIN_max_length = "PIN Code must have 6 Digits";
	private String PIN_message_character = "Special characters are not allowed";
	private String TELE_message_blank = "Telephone no must not be blank";
	private String TELE_character_not_allow = "Characters are not allowed";
	private String TELE_special_character = "Special characters are not allowed";
	private String Email_message_blank = "Email-ID must not be blank";
	private String Email_incorrect_format = "Email-ID is not valid";
	private HomePO homePage;
	private LoginPage loginPage;
	private DashBoardPage dashboard;
	private NewCustomerPage newcustomer;
	
	
	@BeforeSuite
	public void beforeClass () {
		driver = getBrowserDriver(url);
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("Go to Login page");
		homePage.clickToBankProject(driver);
		
		log.info("Login by account");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.InputID(userid);
		loginPage.InputPW(pass);
		loginPage.clickToLoginButton(driver);
		
		dashboard = PageGeneratorManager.getDashBoardPage(driver);
		dashboard.clickToMenuByText("New Customer");
		newcustomer = PageGeneratorManager.getNewCusomerPage(driver);		
	}
	
	@Test
	public void NC01_Name_cannot_be_empty () {
		newcustomer.clickToTextboxByName("name");
		newcustomer.sendTabToFieldByName("name");
		String actual_message = newcustomer.getMessageOfFieldByName("name");
		verifyEquals(actual_message, ex_message_blank);	
	}
	
	@Test
	public void NC02_Name_cannot_be_numberic () {
		newcustomer.inputDataToTextboxByName("name", "1234");
		String actual = newcustomer.getMessageOfFieldByName("name");
		verifyEquals(actual, ex_message_numberic);
	}
	
	@Test
	public void NC03_Check_special_characters () {
		newcustomer.inputDataToTextboxByName("name", "name!@#");
		String actual = newcustomer.getMessageOfFieldByName("name");
		verifyEquals(actual, ex_message_specialCharacter);
	}
	
	@Test
	public void NC04_Name_check_first_character_as_blank() {
		log.info("Input space as first character");
		newcustomer.inputDataToTextboxByName("name", " ");
		String message = newcustomer.getMessageOfFieldByName("name");
		verifyEquals(message, ex_message_first_character);
	}
	
	@Test
	public void NC05_Address_blank() {
		String addr_blank = "ADDRESS cannot be empty";
		log.info("Check address blank");
		newcustomer.clickToAddress();
		newcustomer.sendTabToAddress();
		String message = newcustomer.getMessageOfAddress();
		verifyEquals(message, addr_blank);
	}
	
	@Test
	public void NC06_Address_check_first_character() {
		String addr_first_chracter = "First character cannot be space";
		log.info("Check first character of address");
		newcustomer.inputDataToAddress(" ");
		String message = newcustomer.getMessageOfAddress();
		verifyEquals(message, addr_first_chracter);
	}
	
	@Test
	public void NC08_CityField_cannot_be_empty() {
		newcustomer.clickToTextboxByName("city");
		newcustomer.sendTabToFieldByName("city");
		String actual_message = newcustomer.getMessageOfFieldByName("city");
		verifyEquals(actual_message, city_message_blank);
	}
	
	@Test
	public void NC09_City_numberic() {
		newcustomer.inputDataToTextboxByName("city", "1234");
		String message = newcustomer.getMessageOfFieldByName("city");
		verifyEquals(message, city_message_numberic);
	}
	
	@Test
	public void NC10_City_check_special_characters() {
		newcustomer.inputDataToTextboxByName("city", "city!@#");
		String message = newcustomer.getMessageOfFieldByName("city");
		verifyEquals(message, city_message_special_characters);
	}
	
	@Test
	public void NC11_City_check_first_character() {
		String city_first_character = "First character cannot be space";
		log.info("check first chracter of city field");
		newcustomer.inputDataToTextboxByName("city", " ");
		String message = newcustomer.getMessageOfFieldByName("city");
		verifyEquals(message, city_first_character);
	}
	
	@Test
	public void NC12_Check_state_blank() {
		newcustomer.clickToTextboxByName("state");
		newcustomer.sendTabToFieldByName("state");
		String message = newcustomer.getMessageOfFieldByName("state");
		verifyEquals(message, state_message_blank);
	}
	
	@Test
	public void NC13_Check_state_special_characters() {
		newcustomer.inputDataToTextboxByName("state", "state!@#");
		String message = newcustomer.getMessageOfFieldByName("state");
		verifyEquals(message, state_message_special_characters);
	}
	
	@Test
	public void NC14_Check_state_numberic() {
		newcustomer.inputDataToTextboxByName("state", "1234");
		String message = newcustomer.getMessageOfFieldByName("state");
		verifyEquals(message, state_message_numberic);
	}
	
	@Test
	public void NC15_State_check_first_character() {
		String state_first_character = "First character cannot be space";
		log.info("check first character of state field");
		newcustomer.inputDataToTextboxByName("state", " ");
		String message = newcustomer.getMessageOfFieldByName("state");
		verifyEquals(message, state_first_character);
	}
	
	@Test
	public void NC16_PIN_must_be_numberic() {
		newcustomer.inputDataToTextboxByName("pinno", "123PIN");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, PIN_numberic);
	}
	
	@Test
	public void NC17_PIN_blank() {
		newcustomer.clickToTextboxByName("pinno");
		newcustomer.sendTabToFieldByName("pinno");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, PIN_blank);
	}
	
	@Test
	public void NC18_PIN_max_length() {
		newcustomer.inputDataToTextboxByName("pinno", "1234");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, PIN_max_length);
		newcustomer.inputDataToTextboxByName("pinno", "12345678");
		String value = newcustomer.getValueOfTextboxByName("pinno", "value");
		verifyEquals(value.length(), 6);
	}
	
	@Test
	public void NC19_PIN_special_character() {
		newcustomer.inputDataToTextboxByName("pinno", "pin!@#");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, PIN_message_character);
	}
	
	@Test
	public void NC20_PIN_check_first_space_character() {
		log.info("Check first space character");
		String pin_first_space = "First character cannot be space";
		newcustomer.inputDataToTextboxByName("pinno", " ");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(pin_first_space, message);
	}
	
	@Test
	public void NC21_PIN_include_blank_space() {
		log.info("PIN cannot have blank space");
		String pin_have_space = "PIN cannot have space";
		newcustomer.inputDataToTextboxByName("pinno", "pin 123");
		String message = newcustomer.getMessageOfFieldByName("pinno");
		verifyEquals(pin_have_space, message);
	}
	
	@Test
	public void NC22_Check_telephone_blank() {
		log.info("Click to telephone field");
		newcustomer.clickToTextboxByName("telephoneno");
		
		log.info("Send TAB to telephone field");
		newcustomer.sendTabToFieldByName("telephoneno");
		
		log.info("Get and verify message field must not be blank");
		String message = newcustomer.getMessageOfFieldByName("telephoneno");
		verifyEquals(message, TELE_message_blank);
	}
	
	@Test
	public void NC23_Check_telephone_first_space_character() {
		String tele_first_space_character = "First character cannot be space";
		log.info("input first character as space to telephone field");
		newcustomer.inputDataToTextboxByName("telephoneno", " ");
		
		log.info("Get message and verify character not allowed");
		String message = newcustomer.getMessageOfFieldByName("telephoneno");
		verifyEquals(message, tele_first_space_character);
	}
	
	@Test
	public void NC24_Telephone_cannot_have_space() {
		log.info("Telephone cannot have space");
		String tele_have_space = "Telephone cannot have be space";
		newcustomer.inputDataToTextboxByName("telephoneno", "123 123");
		String message = newcustomer.getMessageOfFieldByName("telephoneno");
		verifyEquals(tele_have_space, message);
	}
	
	@Test
	public void NC25_Check_telephone_special_character() {
		String[] data = {"77646@", "@42637", "14526@545"};
		for(int i = 0; i < data.length ; i++) {
			log.info("input special character to telephone field");
			newcustomer.inputDataToTextboxByName("telephoneno", data[i]);
			
			log.info("Get and verify special character not allowed");
			String message = newcustomer.getMessageOfFieldByName("telephoneno");
			verifyEquals(message, TELE_special_character);
		}
		
	}
	
	@Test
	public void NC26_Check_Email_blank() {
		log.info("Click to Email field");
		newcustomer.clickToTextboxByName("emailid");
		
		log.info("Send tab to move next field");
		newcustomer.sendTabToFieldByName("emailid");
		
		log.info("Get and verify Email must not be blank");
		String message = newcustomer.getMessageOfFieldByName("emailid");
		verifyEquals(message, Email_message_blank);
	}
	
	@Test
	public void NC27_Check_Email_incorrect_format() {
		String email[] = {"guru99@", "guru99gmail.com", "guru99", "guru99@gmail."};
		for(int i=0; i < email.length; i++) {
			log.info("Input Email incorrect format");
			newcustomer.inputDataToTextboxByName("emailid", email[i]);
			
			log.info("Get and verify email  must be correct format");
			String message = newcustomer.getMessageOfFieldByName("emailid");
			verifyEquals(message, Email_incorrect_format);
		}
	}
	
	@Test
	public void NC29_Email_cannot_have_space() {
		log.info("Email cannot have spave");
		log.info("Input email have space to textbox");
		newcustomer.inputDataToTextboxByName("emailid", "guru99 gmail");
		
		log.info("Get and verify message");
		String message = newcustomer.getMessageOfFieldByName("emailid");
		verifyEquals(message, Email_incorrect_format);
	}
	
	@Test
	public void NC30_Check_all_field_name() {
		String fieldName[] = {"Customer Name", "Gender", "Date of Birth", "Address", "City", "State", "PIN", "Mobile Number", "E-mail", "Password"};
		for(int  i=0; i<fieldName.length; i++) {
			log.info("Verify field " + fieldName[i] + "visible");
			newcustomer.verifyFieldNameVisible(fieldName[i]);
		}
	}
	
	@AfterSuite
	public void afterClass() {
		closeBrowserDriver();
	}

}
