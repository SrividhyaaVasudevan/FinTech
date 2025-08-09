package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.ConfigReader;

import java.time.Duration;

public class BrowserInitializer {

    WebDriver driver;
    public WebDriver driverInitializer()
    {
        String browserName = ConfigReader.get("browser").toLowerCase();

        switch (browserName) {
            case "chrome":
                chromeBrowser();
                break;
            case "edge":
                throw new RuntimeException("Edge browser setup not completed");
            case "firefox":
                throw new RuntimeException("Firefox browser setup not completed");
            default:
                throw new RuntimeException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public void chromeBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver(){
        return driver;
    }

}
