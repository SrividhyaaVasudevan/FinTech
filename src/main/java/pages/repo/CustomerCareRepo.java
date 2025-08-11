package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerCareRepo extends UiBase {

    @FindBy(xpath = "//*[@id=\"name\"]")
    protected WebElement nameField;

    @FindBy(xpath = "//*[@id=\"email\"]")
    protected WebElement emailField;

    @FindBy(xpath = "//*[@id=\"phone\"]")
    protected WebElement phoneField;

    @FindBy(xpath = "//input[contains(@value,\"Send\")]")
    protected WebElement sendBtn;

    @FindBy(xpath = "//*[@id=\"message\"]")
    protected WebElement messageField;

    @FindBy(xpath = "//*[@id=\"name.errors\"]")
    protected WebElement nameErr;

    @FindBy(xpath = "//*[@id=\"email.errors\"]")
    protected WebElement emailErr;

    @FindBy(xpath = "//*[@id=\"phone.errors\"]")
    protected WebElement phoneErr;

    @FindBy(xpath = "//*[@id=\"message.errors\"]")
    protected WebElement messageErr;

    @FindBy(xpath = "//*[@id=\"rightPanel\"]/p[2]")
    protected WebElement successMsg;


}
