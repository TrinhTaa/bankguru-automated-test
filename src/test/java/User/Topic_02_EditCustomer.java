package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.EditCustomer;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.PageGeneratorManager;

public class Topic_02_EditCustomer extends BaseTest{
    private static WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
    private String id_edit = "42316";
    private HomePO homepage;
    private LoginPage loginPage;
    private DashBoardPage dashboard;
    private EditCustomer editCustomer;

    @BeforeSuite
    public void beforeClass (){
        driver = getBrowserDriver(url);
        homepage = PageGeneratorManager.getHomePage(driver);
        log.info("Go to Login page");
		homepage.clickToBankProject(driver);
		
		log.info("Login by account");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.InputID(userid);
		loginPage.InputPW(pass);
		loginPage.clickToLoginButton(driver);

        log.info("Go to Edit Customer Page");
        dashboard = PageGeneratorManager.getDashBoardPage(driver);
		dashboard.clickToMenuByText("Edit Customer");
        
    }

    @Test
    public void EC01_CustomerId_not_empty(){
        log.info("Verify Customer Id must not be blank");
        dashboard.clickToTextboxByFieldName();
        dashboard.sendTabToElementByName();
        
        log.info("Get and verify Customer id must not be empty message");
        String message = dashboard.getMessageOfFieldByName();
        verifyEquals(message, "Customer ID is required");
    }

    @Test
    public void EC02_CustomerId_must_be_numberic(){
        String data[] = {"1234Acc", "Acc123"};
        for(int i = 0; i < data.length ; i++){
            log.info("Input character to customer id field");
        dashboard.InputDataByName(data[i]);

        log.info("Get and verify message customer id must be numberic");
        String message = dashboard.getMessageOfFieldByName();
        verifyEquals(message, "Characters are not allowed");
        }
    }

    @Test
    public void EC03_CustomerId_check_special_character(){
        String data[] = {"123!@#", "!@#"};
        for(int i = 0 ; i < data.length ; i++){
            log.info("Input special character to customer id ");
            dashboard.InputDataByName(data[i]);

            log.info("Get and verify message special characterd are not allowed");
            String actual_message = dashboard.getMessageOfFieldByName();
            verifyEquals(actual_message, "Special characters are not allowed");
        }
    }

    @Test
    public void EC04_Check_customerId_valid(){
        dashboard.InputDataByName(id_edit);
        dashboard.ClickToSubmitAccount(driver);
        editCustomer =  PageGeneratorManager.getEditCustomerPage(driver);
        editCustomer.EditCustomerPageShouleBeVisible(driver);
    }
    
    @BeforeTest
    public void beforeEditCustomer () {
    	log.info("Input a customer id valid");
        dashboard.InputDataByName(id_edit);
        dashboard.ClickToSubmitAccount(driver);
        editCustomer =  PageGeneratorManager.getEditCustomerPage(driver);
        editCustomer.EditCustomerPageShouleBeVisible(driver);
    }
    
    @Test
    public void EC05_Check_Adress_field(){
        editCustomer.clickToAddressElement(driver);
        editCustomer.sendTextToAddress("");
        editCustomer.sendTabToAddress(driver);
        String message = editCustomer.getWarningMessageOfAddress();
        verifyEquals(message, "ADDRESS cannot be empty");
    }
    
    @Test
    public void EC06_Check_City_empty() {
    	log.info("Check city field cannot be empty"); 
    	editCustomer.SendTextToElementByName("city", "");
    	editCustomer.sendTabToElementByName("city");
    	String message = editCustomer.getMessageOfFieldByName("city");
    	verifyEquals(message, "CITY cannot be empty");
    }
    
    @Test
    public void EC07_Check_City_Field_must_be_numberic() {
    	log.info("City field cannot contain number");
    	String data[] = {"1234", "city1234"};
    	for(int i=0; i < data.length ; i++ ) {
    		editCustomer.SendTextToElementByName("city", data[i]);
    		String message = editCustomer.getMessageOfFieldByName("city");
    		verifyEquals(message, "CITY cannot contain Number");
    	}
    }
    
    @Test
    public void EC08_Check_City_special_character() {
    	log.info("City cannot contains special characters");
    	String data[]= {"City!@#", "!@#"};
    	for(int i=0; i < data.length; i++) {
    		editCustomer.SendTextToElementByName("city", data[i]);
    		String message = editCustomer.getMessageOfFieldByName("city");
    		verifyEquals(message, "City cannot contains Special Characters");
    	}
    }
    
    @Test
    public void EC09_State_empty() {
    	log.info("check state empty");
    	editCustomer.SendTextToElementByName("state", "");
    	editCustomer.sendTabToElementByName("state");
    	String message = editCustomer.getMessageOfFieldByName("state");
    	verifyEquals(message, "STATE cannot be empty");
    }
    
    @Test
    public void EC10_State_check_numberic() {
    	log.info("Check numberic are not allowed");
    	String data[] = {"state123", "1234"};
    	for(int i=0; i < data.length ; i++) {
    		editCustomer.SendTextToElementByName("state", data[i]);
    		String message = editCustomer.getMessageOfFieldByName("state");
    		verifyEquals(message, "Number are not allowed");
    	}
    }
    
    @Test
    public void EC11_State_check_special_character() {
    	log.info("State cannot contain special character");
    	String data[] = {"State!@#", "!@#"};
    	for(int i=0; i < data.length ; i++) {
    		editCustomer.SendTextToElementByName("state", data[i]);
    		String message = editCustomer.getMessageOfFieldByName("state");
    		verifyEquals(message, "State cannot contain Special Characters");
    	}
    }
    
    @Test
	public void EC12_PIN_must_be_numberic() {
    	log.info("PIN must be numberic");
    	String data[]= {"123PIN", "1234"};
    	for(int i=0; i < data.length ; i++) {
    		editCustomer.SendTextToElementByName("pinno", data[i]);
    		String message = editCustomer.getMessageOfFieldByName("pinno");
    		verifyEquals(message, "PIN cannot contain character");
    	}
	}
	
	@Test
	public void EC13_PIN_blank() {
		editCustomer.SendTextToElementByName("pinno", "");
		editCustomer.sendTabToElementByName("pinno");
		String message = editCustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, "PIN cannot be empty");
	}
	
	@Test
	public void EC14_PIN_max_length() {
		editCustomer.SendTextToElementByName("pinno", "1234");
		String message = editCustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, "PIN must contain 6 digit");
		editCustomer.SendTextToElementByName("pinno", "12345678");
		String value = editCustomer.getValueOfTextboxByName("pinno", "value");
		verifyEquals(value.length(), 6);
	}
	
	@Test
	public void EC15_PIN_special_character() {
		editCustomer.SendTextToElementByName("pinno", "pin!@#");
		String message = editCustomer.getMessageOfFieldByName("pinno");
		verifyEquals(message, "Characters are not allowed");
	}
	
	@Test
	public void EC16_Check_telephone_blank() {
		log.info("Click to telephone field");
		editCustomer.clickToElementByName("telephoneno");
		
		log.info("Send TAB to telephone field");
		editCustomer.sendTabToElementByName("telephoneno");
		
		log.info("Get and verify message field must not be blank");
		String message = editCustomer.getMessageOfFieldByName("telephoneno");
		verifyEquals(message, "Telephone no must not be blank");
	}
	
	@Test
	public void EC17_Check_telephone_special_character() {
		String[] data = {"77646@", "@42637", "14526@545"};
		for(int i = 0; i < data.length ; i++) {
			log.info("input special character to telephone field");
			editCustomer.SendTextToElementByName("emailid", data[i]);
			
			log.info("Get and verify special character not allowed");
			String message = editCustomer.getMessageOfFieldByName("telephoneno");
			verifyEquals(message, "Special characters are not allowed");
		}
		
	}
	
	@Test
	public void EC18_Check_Email_blank() {
		log.info("Click to Email field");
		editCustomer.clickToElementByName("emailid");
		
		log.info("Send tab to move next field");
		editCustomer.sendTabToElementByName("emailid");
		
		log.info("Get and verify Email must not be blank");
		String message = editCustomer.getMessageOfFieldByName("emailid");
		verifyEquals(message, "Email-ID must not be blank");
	}
	
	@Test
	public void EC19_Check_Email_incorrect_format() {
		String email[] = {"guru99@", "guru99gmail.com", "guru99", "guru99@gmail."};
		for(int i=0; i < email.length; i++) {
			log.info("Input Email incorrect format");
			editCustomer.SendTextToElementByName("emailid", email[i]);
			
			log.info("Get and verify email  must be correct format");
			String message = editCustomer.getMessageOfFieldByName("emailid");
			verifyEquals(message, "Email must be in format example@example.com");
		}
	}
	
    @AfterSuite
    public void afterClass (){
        driver.quit();
    }
}
    
