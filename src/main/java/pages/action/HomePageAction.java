package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.repo.HomePageRepo;

public class HomePageAction extends HomePageRepo {
    WebDriver driver;
    public HomePageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isHomepageVisible(){
        return waitUntilElementDisplayed(welcomeMsg);
    }

    public void clickOnOpenAccount(){
        waitUntilElementDisplayed(openNewAccount);
        click(openNewAccount);
    }

    public void clickOnAccountOverview(){
        waitUntilElementDisplayed(accountOverview);
        click(accountOverview);
    }

    public void clickOnTrasferFund(){
        waitUntilElementDisplayed(transferFund);
        click(transferFund);
    }

    public void clickOnBillPay(){
        waitUntilElementDisplayed(billPay);
        click(billPay);
    }

    public void clickOnFindTransactions(){
        waitUntilElementDisplayed(findTransactions);
        click(findTransactions);
    }

    public void clickOnUpdateContact(){
        waitUntilElementDisplayed(updateContact);
        click(updateContact);
    }

    public void clickOnRequestLoan(){
        waitUntilElementDisplayed(requestLoan);
        click(requestLoan);
    }

    public void clickOnCustomerCare(){
        waitUntilElementDisplayed(customerCare);
        click(customerCare);
    }


}
