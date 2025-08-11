package tests;

import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class AccountFunctionalityTest extends BaseTest {

    private String username;
    private String password;

    private String savingsAccount;
    private String checkingAccount;
    private String originalBalance;

    @BeforeClass(dependsOnMethods = "setupClass")
    public void dataSetup(){
        try{
            Map<String, String> data = testData.getExcelData("signin", "validData");
            username = loginPageAction.createAccount(data,"1");
            password = data.get("password");
        }catch (Exception e){
            throw new RuntimeException("Failed to setup data:  " + e.getMessage(), e);
        }

    }

    @Test(priority = -1)
    public void verifyOpenAccount() {
        loginPageAction.login(username, password);
        homePageAction.clickOnAccountOverview();
        accountOverviewPageAction.clickOnFirstAccountNumber();
        originalBalance = accountOverviewPageAction.getBalanceAmount().replaceAll("\\$","");
        homePageAction.clickOnOpenAccount();
        checkingAccount = openAccountPageAction.getFirstAccountNumber();
        openAccountPageAction.selectSavingAccount();
        savingsAccount = openAccountPageAction.clickOnOpenNewAccount();
        AssertFail(openAccountPageAction.checkIfAccountCreated(), "Account is created");
    }

    @Test
    public void verifyCheckingAccountSummary() {
        loginPageAction.login(username, password);
        homePageAction.clickOnAccountOverview();
        String balance = String.valueOf(Double.parseDouble(originalBalance) - 100);
        accountOverviewPageAction.clickOnAccountNumber(checkingAccount);
        AssertFail(accountOverviewPageAction.checkAccountType("CHECKING"),"CHECKING Account type");
        AssertFail(accountOverviewPageAction.checkTransactionPresent(),"CHECKING Account transactions");
        AssertFail(accountOverviewPageAction.checkTransactionAmount(balance),"Verify Transaction balance updated");
    }

    @Test
    public void verifySavingsAccountSummary() {
        loginPageAction.login(username, password);
        homePageAction.clickOnAccountOverview();
        accountOverviewPageAction.clickOnAccountNumber(savingsAccount);
        AssertFail(accountOverviewPageAction.checkAccountType("SAVINGS"),"SAVINGS Account type");
        AssertFail(accountOverviewPageAction.checkTransactionPresent(),"SAVINGS Account transactions");
        AssertFail(accountOverviewPageAction.checkTransactionAmount("100"),"Verify Transaction balance updated");
    }

    @Test
    public void verifyTransactions() {
        loginPageAction.login(username, password);
        homePageAction.clickOnFindTransactions();
        AssertFail(findTransactionsPageAction.selectDateRange(),"Verify date is within range");
        AssertFail(findTransactionsPageAction.checkDebit("100"),"Verify debit value");
    }

}
