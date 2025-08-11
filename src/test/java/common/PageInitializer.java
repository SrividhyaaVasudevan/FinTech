package common;

import com.beust.ah.A;
import org.openqa.selenium.WebDriver;
import pages.action.*;

public class PageInitializer extends BrowserInitializer {

    public LoginPageAction loginPageAction;
    public HomePageAction homePageAction;
    public OpenAccountPageAction openAccountPageAction;
    public AccountOverviewPageAction accountOverviewPageAction;
    public FindTransactionsPageAction findTransactionsPageAction;
    public TransferFundPageAction transferFundPageAction;
    public RequestLoanPageAction requestLoanPageAction;
    public CustomerCarePageAction customerCarePageAction;


    public void initializePages(WebDriver driver){
        loginPageAction = new LoginPageAction(driver);
        homePageAction = new HomePageAction(driver);
        openAccountPageAction = new OpenAccountPageAction(driver);
        accountOverviewPageAction = new AccountOverviewPageAction(driver);
        findTransactionsPageAction = new FindTransactionsPageAction(driver);
        transferFundPageAction = new TransferFundPageAction(driver);
        requestLoanPageAction = new RequestLoanPageAction(driver);
        customerCarePageAction = new CustomerCarePageAction(driver);
    }

}
