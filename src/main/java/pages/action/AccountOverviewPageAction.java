package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.repo.AccountOverviewPageRepo;

public class AccountOverviewPageAction extends AccountOverviewPageRepo {
    WebDriver driver;
    public AccountOverviewPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAccountNumber(String accNo){
        waitUntilElementDisplayed(accOverviewHeader);
        staticWait(2000);
        int i=0;
        for(WebElement acc: accountNumber){
            if(getText(acc).equalsIgnoreCase(accNo)){
                click(accountNumberLink.get(i));
                break;
            }
            i++;
        }
    }

    public void clickOnFirstAccountNumber(){
        waitUntilElementDisplayed(accOverviewHeader);
        staticWait(1000);
        click(accountNumberLink.get(0));
    }

    public boolean checkAccountType(String type){
        waitUntilElementDisplayed(accountType);
        staticWait(3000);
        return getText(accountType).contains(type);
    }

    public boolean checkTransactionPresent(){
        return waitUntilElementDisplayed(balance.get(0))
                && waitUntilElementDisplayed(transaction.get(0));
    }

    public boolean checkTransactionAmount(String amount){
        waitUntilElementDisplayed(balanceSummary);
        staticWait(2000);
        return getText(balanceSummary).contains(amount);
    }

    public boolean checkBalanceAmount(String amount){
        waitUntilElementDisplayed(accOverviewHeader);
        staticWait(2000);
        for(WebElement b: balance){
            if(getText(b).equalsIgnoreCase(amount)){
                return true;
            }
        }
        return false;
    }

    public String getBalanceAmount(){
        waitUntilElementDisplayed(balanceSummary);
        staticWait(2000);
        return getText(balanceSummary);
    }

}
