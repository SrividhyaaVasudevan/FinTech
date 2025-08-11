package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountOverviewPageRepo extends UiBase {

    @FindBy(xpath = "//h1[contains(text(),\"Accounts Overview\")]")
    protected WebElement accOverviewHeader;

    @FindBy(xpath = "//*[@id=\"accountTable\"]/tbody/tr/td[1]")
    protected List<WebElement> accountNumber;

    @FindBy(xpath = "//*[@id=\"accountTable\"]/tbody/tr/td[1]/a")
    protected List<WebElement> accountNumberLink;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr/td[1]/following-sibling::td[1]")
    protected List<WebElement> balance;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr/td[1]/following-sibling::td[2]")
    protected List<WebElement> availableAmount;

    @FindBy(xpath = "//*[@id=\"accountType\"]")
    protected WebElement accountType;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr/td[2]/a")
    protected List<WebElement> transaction;

    @FindBy(xpath = "//*[@id=\"balance\"]")
    protected WebElement balanceSummary;


}
