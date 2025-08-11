package tests;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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


    @Test(dataProvider = "mapData", dependsOnMethods = "verifyCreateAccount")
    public void verifyLoginFunctionality(Map<String, String > data) {

        if(data.get("scenario").equalsIgnoreCase("valid")){
            loginPageAction.fillLoginDetails(username, password);
            AssertFail(loginPageAction.checkIfPasswordMasked(), "Verify if Password field Masked");
            loginPageAction.clickOnLogin();
            AssertFail(homePageAction.isHomepageVisible(), "Login successful");
        }else {
            loginPageAction.login(data.get("username"), data.get("password"));
            AssertFail(loginPageAction.checkErrorMessage(data.get("error")) ||
                    loginPageAction.checkErrorMessage(data.get("AltError")), "Error message displayed");
        }
    }


    @DataProvider(name = "mapData")
    public Object[][] provideObjectData() {
        return testData.provideData("login");
    }


}
