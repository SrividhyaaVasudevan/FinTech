package tests;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends BaseTest {

    private String username;
    private String password;

    @Test
    public void verifyCreateAccount() {
        Map<String, String> data = testData.getExcelData("signin", "validData");
        username = loginPageAction.createAccount(data,"3");
        password = data.get("password");
        AssertFail(loginPageAction.checkIfAccountCreated(), "Account created");
        System.out.println(username);
        System.out.println(password);
    }

    @Test(dependsOnMethods = "verifyCreateAccount")
    public void verifyLoginWithValidCredentials() {
        Map<String, String> data = testData.getExcelData("login", "validData");
        loginPageAction.login(username, password);
        AssertFail(homePageAction.isHomepageVisible(), "Login successful");
    }

    @Test
    public void verifyLoginWithIncorrectPassword() {
        Map<String, String> data = testData.getExcelData("login", "InvalidPassword");
        loginPageAction.login(data.get("username"), data.get("password"));
        AssertFail(loginPageAction.checkErrorMessage(data.get("error")), "Error message displayed");
    }

    @Test
    public void verifyLoginWithUnregisteredUser() {
        Map<String, String> data = testData.getExcelData("login", "unregistered");
        loginPageAction.login(data.get("username"), data.get("password"));
        AssertFail(loginPageAction.checkErrorMessage(data.get("error")), "Error message displayed");
    }

    @Test
    public void verifyLoginWithEmptyField() {
        Map<String, String> data = testData.getExcelData("login", "EmptyData");
        loginPageAction.login(data.get("username"), data.get("password"));
        AssertFail(loginPageAction.checkErrorMessage(data.get("error")), "Error message displayed");
    }

    @Test
    public void verifyLoginWithInvalidEmailFormat() {
        Map<String, String> data = testData.getExcelData("login", "InvalidEmailFormat");
        loginPageAction.login(data.get("username"), data.get("password"));
        AssertFail(loginPageAction.checkErrorMessage(data.get("error")), "Error message displayed");
    }

    @Test
    public void verifyPasswordInputFieldMasked() {
        Map<String, String> data = testData.getExcelData("login", "validData");
        loginPageAction.fillLoginDetails(data.get("username"), data.get("password"));
        AssertFail(loginPageAction.checkIfPasswordMasked(), "Verify if Password field Masked");
    }

}
