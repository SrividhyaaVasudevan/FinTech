package tests;

import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class CustomerSupportTest extends BaseTest {

    private String username;
    private String password;

    @BeforeClass
    public void dataSetup(){
        Map<String, String> data = testData.getExcelData("signin", "validData");
        username = loginPageAction.createAccount(data,"5");
        password = data.get("password");
    }

    @Test
    public void verifyValidCustomerCareMsg() {
        Map<String, String> data = testData.getExcelData("support", "validData");
        loginPageAction.login(username, password);
        homePageAction.clickOnCustomerCare();
        customerCarePageAction.fillDetails(data.get("name"), data.get("email"), data.get("phone"), data.get("message"));
        customerCarePageAction.clickOnSend();
        AssertFail(customerCarePageAction.checkSuccessMsg(),"Customer Support Message Sent");
    }

    @Test
    public void verifyCustomerCareEmptyField() {
        Map<String, String> data = testData.getExcelData("support", "missingField");
        loginPageAction.login(username, password);
        homePageAction.clickOnCustomerCare();
        customerCarePageAction.fillDetails(data.get("name"), data.get("email"), data.get("phone"), data.get("message"));
        customerCarePageAction.clickOnSend();
        AssertFail(customerCarePageAction.checkNameError(),"Verify Name error");
        AssertFail(customerCarePageAction.checkEmailError(),"Verify Email error");
        AssertFail(customerCarePageAction.checkPhoneError(),"Verify Phone error");
        AssertFail(customerCarePageAction.checkMsgError(),"Verify Message error");
    }

}
