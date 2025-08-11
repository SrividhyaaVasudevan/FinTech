package pages.action;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.repo.LoginPageRepo;

import java.util.Map;

public class LoginPageAction extends LoginPageRepo {
    WebDriver driver;
    public LoginPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillLoginDetails(String username, String password){
        waitUntilElementDisplayed(usernameField);
        fill(usernameField, username);
        fill(passwordField, password);
    }

    public void clickOnLogin(){
        waitUntilElementDisplayed(loginBtn);
        click(loginBtn);
    }

    public void login(String username, String password){
        fillLoginDetails(username, password);
        clickOnLogin();
    }

    public String createAccount(Map<String, String> data, String uniqueCH){
        waitUntilElementDisplayed(registerBtn);
        click(registerBtn);
        waitUntilElementDisplayed(firstName);
        staticWait(2000);
        String name = generateUniqueUsername(uniqueCH);
        fill(firstName, data.get("firstname"));
        fill(lastName, data.get("lastname"));
        fill(streetField, data.get("street"));
        fill(cityField, data.get("city"));
        fill(stateField, data.get("state"));
        fill(zipcodeField, data.get("zip"));
        fill(phoneField, data.get("phone"));
        fill(sssField, data.get("ssn"));
        fill(usernameRegisterField, name);
        fill(passwordRegisterField, data.get("password"));
        fill(confirmPassword, data.get("confirmpassword"));
        click(registerButton);
        return name;

    }

    public boolean checkIfAccountCreated(){
        return waitUntilElementDisplayed(accountCreatedMsg);
    }

    public boolean checkErrorMessage(String error){
        waitUntilElementDisplayed(errorMsg);
        return getText(errorMsg).equalsIgnoreCase(error);
    }

    public boolean checkIfPasswordMasked(){
        waitUntilElementDisplayed(passwordField);
        return getAttribute(passwordField, "type").equalsIgnoreCase("password");
    }

    public WebElement getUserNameElement(){
        waitUntilElementDisplayed(usernameField);
        staticWait(2000);
        return usernameField;
    }

    public WebElement getPasswordElement(){
        return passwordField;
    }

    public WebElement getUserNameLabelElement(){
        waitUntilElementDisplayed(usernameLabel);
        staticWait(2000);
        return usernameLabel;
    }

    public WebElement getPasswordLabelElement(){
        return passwordField;
    }

    public boolean responsiveTest(){
        driver.manage().window().setSize(new Dimension(400, 800));
        waitUntilElementDisplayed(usernameField);
        staticWait(5000);
        return isDisplayed(usernameField);
    }

}
