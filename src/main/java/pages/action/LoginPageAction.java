package pages.action;


import common.UiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPageAction extends UiBase {
    WebDriver driver;
    public LoginPageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
}
