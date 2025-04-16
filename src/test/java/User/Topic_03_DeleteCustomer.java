package User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.DashBoardPage;
import pageObjects.DeleteCustomer;
import pageObjects.EditCustomer;
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
    public void DC_01_Check_CustomerId_empty(){
        String ex_ID_empty = "Customer ID is required";
        log.info("Click to customer id");
        deletePage.clickToTextboxByFieldName("cusid");
        deletePage.sendTabToFieldByname("cusid");

        log.info("Get and verify message customer id must not be empty");
        String message = deletePage.getMessageOfFieldByName("cusid");
        verifyEquals(message, ex_ID_empty);
    }
    
}
