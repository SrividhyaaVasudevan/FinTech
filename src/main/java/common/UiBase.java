package common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class UiBase{


    WebDriver driver;

    protected void setDriver(WebDriver driver){
        this.driver = driver;
    }
    protected boolean waitUntilElementDisplayed(WebElement element, int timeoutInSeconds , int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
        return isDisplayed(element);
    }

    protected boolean waitUntilElementDisplayed(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
        return isDisplayed(element);
    }

    protected  boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }

    protected  boolean isEnabled(WebElement element){
        return element.isEnabled();
    }

    protected  boolean isSelected(WebElement element){
        return element.isSelected();
    }

    protected  void click(WebElement element){
        element.click();
    }

    public void staticWait(int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected  void clicker(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    protected void fill(WebElement element, String text){
        element.sendKeys(text);
    }

    protected String getText(WebElement element){
        return element.getText();
    }

    protected String getAttribute(WebElement element, String Attribute){
        return element.getAttribute(Attribute);
    }

    protected boolean isEmpty(WebElement element){
        return element.getAttribute("value").isEmpty();
    }

    protected void clearAndFill(WebElement element, String text){
        clear(element);
        staticWait(500);
        element.sendKeys(text);
    }

    protected void clear(WebElement element){
        click(element);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        staticWait(1000);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }

    public static String generateUniqueEmail() {
        String timeStamp = new SimpleDateFormat("MMddHHmmss").format(new Date());
        return "srividhyaa" + timeStamp + "@test.com";
    }

    public static String generateUniquePhoneNumber() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

    public boolean acceptAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            System.out.println("No alert appeared within the timeout period");
            return false;
        }
    }

    public boolean checkAlert(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


}
