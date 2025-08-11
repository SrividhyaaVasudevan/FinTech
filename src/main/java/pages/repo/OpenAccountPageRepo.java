package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenAccountPageRepo extends UiBase {

    @FindBy(xpath = "//h1[contains(text(),\"Open New\")]")
    protected WebElement openNewHeader;

    @FindBy(xpath = "//*[@id=\"type\"]")
    protected WebElement accountTypeDropdown;

    @FindBy(xpath = "//*[@id=\"openAccountForm\"]//input")
    protected WebElement openAccBtn;

    @FindBy(xpath = "//*[@id=\"fromAccountId\"]/option[1]")
    protected WebElement firstAccount;

    @FindBy(xpath = "//*[@id=\"newAccountId\"]")
    protected WebElement newAccountID;



}
