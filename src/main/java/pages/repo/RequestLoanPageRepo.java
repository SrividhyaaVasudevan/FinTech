package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequestLoanPageRepo extends UiBase {

    @FindBy(xpath = "//*[@id=\"fromAccountId\"]")
    protected WebElement fromAccountId;

    @FindBy(xpath = "//*[@id=\"downPayment\"]")
    protected WebElement downPaymentField;

    @FindBy(xpath = "//*[@id=\"amount\"]")
    protected WebElement amountField;

    @FindBy(xpath = "//*[@value=\"Apply Now\"]")
    protected WebElement applyBtn;

    @FindBy(xpath = "//*[@id=\"loanRequestApproved\"]/p[1]")
    protected WebElement loanApprovedMsg;

    @FindBy(xpath = "//*[@id=\"requestLoanError\"]/p")
    protected WebElement errorMsg;

    @FindBy(xpath = "//*[@id=\"loanRequestDenied\"]/p")
    protected WebElement loanDenied;




}
