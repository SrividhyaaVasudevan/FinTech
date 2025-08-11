package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.repo.FindTransactionsPageRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FindTransactionsPageAction extends FindTransactionsPageRepo {
    WebDriver driver;
    public FindTransactionsPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean selectDateRange(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateValue = sdf.format(new Date());
        waitUntilElementDisplayed(fromDateField);
        fill(fromDateField, dateValue);
        fill(toDateField, dateValue);
        click(findByDateRange);
        return checkDate(dateValue);
    }

    public boolean checkDate(String dateValue){
        waitUntilElementDisplayed(date);
        staticWait(3000);
        return getText(date).contains(dateValue);
    }

    public boolean checkCredit(String creditValue){
        waitUntilElementDisplayed(credit);
        staticWait(3000);
        return getText(credit).contains(creditValue);
    }

    public boolean checkDebit(String debitValue){
        waitUntilElementDisplayed(debit);
        staticWait(3000);
        return getText(debit).contains(debitValue);
    }

}
