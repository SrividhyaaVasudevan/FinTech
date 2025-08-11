package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.repo.CustomerCareRepo;
import pages.repo.CustomerCareRepo;

public class CustomerCarePageAction extends CustomerCareRepo {
    WebDriver driver;
    public CustomerCarePageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillDetails(String name, String email, String phone, String message){
        waitUntilElementDisplayed(nameField);
        fill(nameField, name);
        fill(emailField, email);
        fill(phoneField, phone);
        fill(messageField, message);
    }

    public void clickOnSend(){
        waitUntilElementDisplayed(sendBtn);
        click(sendBtn);
    }

    public boolean checkSuccessMsg(){
        waitUntilElementDisplayed(successMsg);
        staticWait(1000);
        return isDisplayed(successMsg);
    }

    public boolean checkPhoneError(){
        waitUntilElementDisplayed(phoneErr);
        staticWait(1000);
        return isDisplayed(phoneErr);
    }

    public boolean checkNameError(){
        waitUntilElementDisplayed(nameErr);
        staticWait(1000);
        return isDisplayed(nameErr);
    }

    public boolean checkEmailError(){
        waitUntilElementDisplayed(emailErr);
        staticWait(1000);
        return isDisplayed(emailErr);
    }

    public boolean checkMsgError(){
        waitUntilElementDisplayed(messageErr);
        staticWait(1000);
        return isDisplayed(messageErr);
    }

}
