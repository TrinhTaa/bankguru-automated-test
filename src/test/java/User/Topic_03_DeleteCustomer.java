package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.DeleteCustomer;
import pageObjects.HomePO;
import pageObjects.LoginPage;
import pageObjects.PageGeneratorManager;

public class Topic_03_DeleteCustomer extends BaseTest {
    private static WebDriver driver;
	private String url = "https://demo.guru99.com/V4";
	private String userid = "mngr616520";
	private String pass = "etuhure";
    private HomePO homepage;
    private LoginPage loginPage;
    private DashBoardPage dashboard;
    private DeleteCustomer deletePage;

    @BeforeSuite
    public void beforeSuite (){
        driver = getBrowserDriver(url);
        homepage = PageGeneratorManager.getHomePage(driver);
        log.info("Go to login page");
        homepage.clickToBankProject(driver);

        log.info("Login by account");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.InputID(userid);
        loginPage.InputPW(pass);
        loginPage.clickToLoginButton(driver);

        log.info("Go to delete customer page");
        dashboard = PageGeneratorManager.getDashBoardPage(driver);
        dashboard.clickToMenuByText("Delete Customer");
        deletePage = PageGeneratorManager.getDeletePage(driver);
    }

    @Test
    public void DC01_Check_CustomerId_empty(){
        String ex_ID_empty = "Customer ID is required";
        log.info("Click to customer id");
        deletePage.clickToTextboxByFieldName("cusid");
        deletePage.sendTabToFieldByname("cusid");

        log.info("Get and verify message customer id must not be empty");
        String message = deletePage.getMessageOfFieldByName("cusid");
        verifyEquals(message, ex_ID_empty);
    }
    
    @Test
    public void DC02_CustomerId_must_be_numberic() {
    	String ID_must_be_numberic = "Customer ID must be numberic";
    	String data[] = {"1234@", "acc1234"};
    	for(int i=0; i<data.length; i++) {
    		log.info("input id have character");
    		deletePage.SendDataToElementByName("cusid", data[i]);
    		String message = deletePage.getMessageOfFieldByName("cusid");
    		verifyEquals(message, ID_must_be_numberic);
    	}   	
    }
    
    @Test
    public void DC03_CustomerId_cannot_have_special_character() {
    	String ID_exclude_special_character = "Special character are not allowed";
    	String data[]= {"123!@#", "!@#$"};
    	for(int i=0; i<data.length; i++) {
    		log.info("input data have special character to ID field");
    		deletePage.SendDataToElementByName("cusid", data[i]);
    		log.info("verify message about special character");
    		String message = deletePage.getMessageOfFieldByName("cusid");
    		verifyEquals(message, ID_exclude_special_character);
    	}
    }
    
    @Test
    public void DC04_CustomerId_cannot_have_blank_space() {
    	String ID_cannot_have_space = "Customer ID cannot have blank space";
    	log.info("input data have blank space to customer id");
    	deletePage.SendDataToElementByName("cusid", "123 456");
    	log.info("verify message customer cannot have space");
    	String message = deletePage.getMessageOfFieldByName("cusid");
    	verifyEquals(ID_cannot_have_space, message);
    }
    
    @Test
    public void DC05_First_character_cannot_be_space() {
    	String first_character_cannot_be_space = "Customer ID cannot have first character space";
    	log.info("Enter first character space");
    	deletePage.SendDataToElementByName("cusid", " 1234");
    	log.info("verify message");
    	String message = deletePage.getMessageOfFieldByName("cusid");
    	verifyEquals(first_character_cannot_be_space, message);
    }
    
    @AfterSuite
    public void afterSuite() {
    	driver.quit();
    }
    
}
