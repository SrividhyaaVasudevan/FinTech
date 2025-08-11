package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.repo.RequestLoanPageRepo;

public class RequestLoanPageAction extends RequestLoanPageRepo {
    WebDriver driver;
    public RequestLoanPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectFromAccountID(String acc){
        waitUntilElementDisplayed(fromAccountId);
        staticWait(1000);
        Select select = new Select(fromAccountId);
        select.selectByVisibleText(acc);
    }

    public void fillLoanDetails(String loan, String downPayment){
        waitUntilElementDisplayed(fromAccountId);
        fill(amountField, loan);
        fill(downPaymentField, downPayment);
    }

    public void applyLoan(String loan, String downPayment){
        fillLoanDetails(loan, downPayment);
        click(applyBtn);
    }

    public boolean checkLoanApproveMsg(){
        waitUntilElementDisplayed(loanApprovedMsg);
        staticWait(2000);
        return isDisplayed(loanApprovedMsg);
    }

    public boolean checkErrorMsg(){
        waitUntilElementDisplayed(errorMsg);
        staticWait(2000);
        return isDisplayed(errorMsg);
    }

    public boolean checkLoanLimitMsg(){
        waitUntilElementDisplayed(loanDenied);
        staticWait(2000);
        return isDisplayed(loanDenied);
    }

}
