package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.repo.TransferFundPageRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TransferFundPageAction extends TransferFundPageRepo {
    WebDriver driver;
    public TransferFundPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectToAccountID(String acc){
        waitUntilElementDisplayed(toAccountId);
        staticWait(1000);
        Select select = new Select(toAccountId);
        select.selectByVisibleText(acc);
    }

    public void selectFromAccountID(String acc){
        waitUntilElementDisplayed(fromAccountId);
        staticWait(1000);
        Select select = new Select(fromAccountId);
        select.selectByVisibleText(acc);
    }

    public boolean transferAmount(String amt){
        waitUntilElementDisplayed(amount);
        fill(amount, amt);
        click(transfer);
        return waitUntilElementDisplayed(transactionMsg);
    }

    public boolean transferSuccessMsg(){
        waitUntilElementDisplayed(transactionMsg);
        staticWait(1000);
        return isDisplayed(transactionMsg);
    }

}
