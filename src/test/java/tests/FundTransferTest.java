package tests;

import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class FundTransferTest extends BaseTest {

    private String username;
    private String password;
    private String checkingAccount;
    private  String savingsAccount;

    @BeforeClass
    public void dataSetup(){
        Map<String, String> data = testData.getExcelData("signin", "validData");
        username = loginPageAction.createAccount(data,"2");
        password = data.get("password");
        homePageAction.clickOnOpenAccount();
        checkingAccount = openAccountPageAction.getFirstAccountNumber();
        openAccountPageAction.selectSavingAccount();
        savingsAccount = openAccountPageAction.clickOnOpenNewAccount();
    }

    @Test
    public void verifyInternalFundTransfer() {
        loginPageAction.login(username, password);
        homePageAction.clickOnAccountOverview();
        accountOverviewPageAction.clickOnAccountNumber(checkingAccount);
        String amt = accountOverviewPageAction.getBalanceAmount().replaceAll("\\$","");
        String sav = String.valueOf(Double.parseDouble(amt) + 100);
        homePageAction.clickOnTrasferFund();
        transferFundPageAction.selectFromAccountID(checkingAccount);
        transferFundPageAction.selectToAccountID(savingsAccount);
        transferFundPageAction.transferAmount(amt);
        homePageAction.clickOnAccountOverview();
        accountOverviewPageAction.clickOnAccountNumber(checkingAccount);
        AssertFail(accountOverviewPageAction.checkTransactionAmount("$0"),"Verify Transaction balance updated");
        homePageAction.clickOnAccountOverview();
        accountOverviewPageAction.clickOnAccountNumber(savingsAccount);
        AssertFail(accountOverviewPageAction.checkTransactionAmount(sav),"Verify Transaction balance updated");
    }

    @Test(priority = 1)
    public void verifyInsufficientBalance() {
        loginPageAction.login(username, password);
        homePageAction.clickOnTrasferFund();
        transferFundPageAction.selectFromAccountID(checkingAccount);
        transferFundPageAction.selectToAccountID(savingsAccount);
        transferFundPageAction.transferAmount("100000");
        AssertFail(!transferFundPageAction.transferSuccessMsg(), "Should Display insufficient Msg");
    }

    @Test
    public void verifyTransferToSameAccount() {
        loginPageAction.login(username, password);
        homePageAction.clickOnTrasferFund();
        transferFundPageAction.selectFromAccountID(checkingAccount);
        transferFundPageAction.selectToAccountID(checkingAccount);
        transferFundPageAction.transferAmount("10");
        AssertFail(!transferFundPageAction.transferSuccessMsg(), "Cannot transfer to the same account.");
    }

}
