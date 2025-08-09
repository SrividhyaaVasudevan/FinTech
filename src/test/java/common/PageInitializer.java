package common;

import org.openqa.selenium.WebDriver;
import pages.action.*;

public class PageInitializer extends BrowserInitializer {

    public LoginPageAction loginPageAction;


    public void initializePages(WebDriver driver){
        loginPageAction = new LoginPageAction(driver);
    }

}
