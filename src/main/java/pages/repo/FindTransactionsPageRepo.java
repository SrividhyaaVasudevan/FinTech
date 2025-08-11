package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindTransactionsPageRepo extends UiBase {

    @FindBy(xpath = "//h1[contains(text(),\"Find Transactions\")]")
    protected WebElement findTransactionHeader;

    @FindBy(xpath = "//*[@id=\"type\"]")
    protected WebElement accountTypeDropdown;

    @FindBy(xpath = "//*[@id=\"fromDate\"]")
    protected WebElement fromDateField;

    @FindBy(xpath = "//*[@id=\"toDate\"]")
    protected WebElement toDateField;

    @FindBy(xpath = "//*[@id=\"findByDateRange\"]")
    protected WebElement findByDateRange;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    protected WebElement date;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    protected WebElement transaction;

    @FindBy(xpath = "//tbody/tr[1]/td[4]")
    protected WebElement credit;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    protected WebElement debit;



}
