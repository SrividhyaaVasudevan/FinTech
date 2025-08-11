package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.repo.OpenAccountPageRepo;

public class OpenAccountPageAction extends OpenAccountPageRepo {
    WebDriver driver;
    public OpenAccountPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectSavingAccount(){
        waitUntilElementDisplayed(accountTypeDropdown);
        Select select = new Select(accountTypeDropdown);
        select.selectByVisibleText("SAVINGS");
    }

    public String getFirstAccountNumber(){
        waitUntilElementDisplayed(firstAccount);
        return getText(firstAccount);
    }

    public String clickOnOpenNewAccount(){
        waitUntilElementDisplayed(openAccBtn);
        staticWait(2000);
        click(openAccBtn);
        waitUntilElementDisplayed(newAccountID);
        return getText(newAccountID);
    }

    public boolean checkIfAccountCreated(){
        return waitUntilElementDisplayed(newAccountID);
    }

}
