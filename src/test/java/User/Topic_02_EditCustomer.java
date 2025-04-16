package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
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
        String CusId_empty = "Customer ID is required";
        log.info("Verify Customer Id must not be blank");
        dashboard.clickToTextboxByFieldName();
        dashboard.sendTabToElementByName();
        
        log.info("Get and verify Customer id must not be empty message");
        String message = dashboard.getMessageOfFieldByName();
        verifyEquals(message, CusId_empty);
    }

    @Test
    public void EC02_CustomerId_must_be_numberic(){
        String CusId_numberic = "Characters are not allowed";
        String data[] = {"1234Acc", "Acc123"};
        for(int i = 0; i < data.length ; i++){
            log.info("Input character to customer id field");
        dashboard.InputDataByName(data[i]);

        log.info("Get and verify message customer id must be numberic");
        String message = dashboard.getMessageOfFieldByName();
        verifyEquals(message, CusId_numberic);
        }
    }

    @Test
    public void EC03_CustomerId_check_special_character(){
        String Message_special = "Special characters are not allowed";
        String data[] = {"123!@#", "!@#"};
        for(int i = 0 ; i < data.length ; i++){
            log.info("Input special character to customer id ");
            dashboard.InputDataByName(data[i]);

            log.info("Get and verify message special characterd are not allowed");
            String actual_message = dashboard.getMessageOfFieldByName();
            verifyEquals(actual_message, Message_special);
        }
    }

    @Test
    public void EC04_Check_customerId_valid(){
        log.info("Input a customer id valid");
        dashboard.InputDataByName(id_edit);
        dashboard.ClickToSubmitAccount(driver);
        editCustomer =  PageGeneratorManager.getEditCustomerPage(driver);
        
    }

    @AfterSuite
    public void afterClass (){
        driver.quit();
    }
}
    
