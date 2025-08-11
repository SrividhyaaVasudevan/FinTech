package tests;

import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class LoanTest extends BaseTest {

    private String username;
    private String password;

    @BeforeClass(dependsOnMethods = "setupClass")
    public void dataSetup(){
        try{
            Map<String, String> data = testData.getExcelData("signin", "validData");
            username = loginPageAction.createAccount(data, "4");
            password = data.get("password");
        } catch (Exception e){
            throw new RuntimeException("Failed to setup data:  " + e.getMessage(), e);
        }
    }

    @Test
    public void verifyLoanRequest() {
        loginPageAction.login(username, password);
        homePageAction.clickOnRequestLoan();
        requestLoanPageAction.applyLoan("1000", "10");
        AssertFail(requestLoanPageAction.checkLoanApproveMsg(),"Loan Applied");
    }

    @Test
    public void verifyMissingField() {
        loginPageAction.login(username, password);
        homePageAction.clickOnRequestLoan();
        requestLoanPageAction.applyLoan("", "");
        AssertFail(requestLoanPageAction.checkErrorMsg(), "Missing Fields error" );
    }

    @Test
    public void verifyLoanLimit() {
        loginPageAction.login(username, password);
        homePageAction.clickOnRequestLoan();
        requestLoanPageAction.applyLoan("100000000", "1");
        AssertFail(requestLoanPageAction.checkLoanLimitMsg(), "Loan Denied" );
    }

}
