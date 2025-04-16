package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.EditCustomerUI;

public class EditCustomer extends BasePage{
    WebDriver driver;

    EditCustomer (WebDriver driver){
        this.driver = driver;
    }

    public void clickToTextboxByFieldName(String name) {
        waitForElementClickable(driver, EditCustomerUI.Dynamic_input, name);
        clickToElement(driver, EditCustomerUI.Dynamic_input, name);
    }

    public void sendTabToElementByName(String name) {
        pressKeyToElement(driver, EditCustomerUI.Dynamic_input, Keys.TAB, name);
    }

    public String getMessageOfFieldByName(String fieldName) {
        waitForElementVisible(driver, EditCustomerUI.Dynamic_message, fieldName);
        String message = getElementText(driver, EditCustomerUI.Dynamic_message, fieldName);
        return message;
    }

    public void inputDataToTextboxByName(String name, String value) {
       waitForElementVisible(driver, EditCustomerUI.Dynamic_input, value, name);
       clickToElement(driver, EditCustomerUI.Dynamic_input, value, name);
    }

    public void ClickToSubmitButton(WebDriver driver) {
        waitForElementClickable(driver, EditCustomerUI.Submit_button);
        clickToElement(driver, EditCustomerUI.Submit_button);
    }

    public void ClickToSubmitAccount(WebDriver driver2) {
        waitForElementClickable(driver2, EditCustomerUI.SubmitAcc_button);
        clickToElement(driver2, EditCustomerUI.SubmitAcc_button);
    }

}
