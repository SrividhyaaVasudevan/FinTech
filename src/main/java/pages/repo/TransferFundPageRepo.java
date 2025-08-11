package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferFundPageRepo extends UiBase {

    @FindBy(xpath = "//*[@id=\"fromAccountId\"]")
    protected WebElement fromAccountId;

    @FindBy(xpath = "//*[@id=\"toAccountId\"]")
    protected WebElement toAccountId;

    @FindBy(xpath = "//*[@id=\"amount\"]")
    protected WebElement amount;

    @FindBy(xpath = "(//*[@id=\"transferForm\"]//input)[2]")
    protected WebElement transfer;

    @FindBy(xpath = "//*[@id=\"showResult\"]/p[1]")
    protected WebElement transactionMsg;



}
