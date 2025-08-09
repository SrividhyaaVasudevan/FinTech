package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.repo.HomePageRepo;

public class HomePageAction extends HomePageRepo {
    WebDriver driver;
    public HomePageAction(WebDriver driver){
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
}
