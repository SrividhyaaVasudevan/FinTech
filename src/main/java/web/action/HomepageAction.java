package web.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import web.repo.HomePageRepo;

public class HomepageAction extends HomePageRepo {
    WebDriver driver;
    public HomepageAction(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
