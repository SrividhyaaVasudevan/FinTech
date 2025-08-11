package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageRepo extends UiBase {

    @FindBy(xpath = "//*[@id=\"leftPanel\"]/p")
    protected WebElement welcomeMsg;
    @FindBy(xpath = "//a[contains(text(),\"Open New\")]")
    protected WebElement openNewAccount;
    @FindBy(xpath = "//a[contains(text(),\"Accounts Overview\")]")
    protected WebElement accountOverview;
    @FindBy(xpath = "//a[contains(text(),\"Transfer Funds\")]")
    protected WebElement transferFund;
    @FindBy(xpath = "//a[contains(text(),\"Bill Pay\")]")
    protected WebElement billPay;
    @FindBy(xpath = "//a[contains(text(),\"Find Transactions\")]")
    protected WebElement findTransactions;
    @FindBy(xpath = "//a[contains(text(),\"Update Contact Info\")]")
    protected WebElement updateContact;
    @FindBy(xpath = "//a[contains(text(),\"Request Loan\")]")
    protected WebElement requestLoan;
    @FindBy(xpath = "//*[@id=\"headerPanel\"]//a[contains(text(),\"contact\")]")
    protected WebElement customerCare;

}
