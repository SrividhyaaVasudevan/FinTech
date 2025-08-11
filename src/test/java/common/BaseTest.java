package common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigReader;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExcelUtils;
import utils.ExtentReportManager;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest extends PageInitializer {

    private WebDriver driver;
    public ExcelUtils testData = new ExcelUtils("testdata.xlsx");
    public WebDriver setup(String browser){
        driver = driverInitializer(browser);
        initializePages(driver);
        return driver;
    }

    @BeforeSuite
    public void setupSuite() {
        // Initialize ExtentReports once for the entire suite
        ExtentReportManager.initReports();
    }

    @Parameters({"browser"})
    @BeforeClass
    public void setupClass(@Optional("chrome") String browser) {
        try{
            driver = setup(browser);
            driver.get(ConfigReader.get("baseUrl"));
        }catch (Exception e){
            if (driver != null){
                driver.quit();
            }
            throw new RuntimeException("Failed to setup driver: " + e.getMessage(), e);
        }
    }

    @BeforeMethod()
    public void setupMethod(Method method) {

        try{
            //clear cookies and load url
            driver.manage().deleteAllCookies();
            driver.get(ConfigReader.get("baseUrl"));

            // Set driver in ExtentReportManager for this thread
            ExtentReportManager.setDriver(driver);
            // Create test in ExtentReport with method name
            String testName = method.getName();
            String description = "Executing test: " + testName;

            // Get test description from @Test annotation if available
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                if (!testAnnotation.description().isEmpty()) {
                    description = testAnnotation.description();
                }
            }

            ExtentReportManager.createTest(testName, description);
            ExtentReportManager.logInfo("Test Started: " + testName);
        }catch (Exception e){
            throw new RuntimeException("Failed in SetupMethod: " + e.getMessage(), e);
        }

    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        // Log test result based on TestNG result
        String testName = result.getMethod().getMethodName();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentReportManager.logPass("Test Passed: " + testName);
                break;
            case ITestResult.FAILURE:
                ExtentReportManager.logFail("Test Failed: " + testName, result.getThrowable());
                break;
            case ITestResult.SKIP:
                ExtentReportManager.logSkip("Test Skipped: " + testName);
                break;
        }

        ExtentReportManager.logInfo("Test Completed: " + testName);
        ExtentReportManager.removeTest(); // Clean up ThreadLocal (includes driver)
        //clear cookies and load url
        driver.manage().deleteAllCookies();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        // Flush the ExtentReports
        ExtentReportManager.flushReports();
        System.out.println("ExtentReports generated at: " + ExtentReportManager.getReportPath());
    }

    protected void logStep(String message) {
        ExtentReportManager.logInfo(message);
    }

    protected void logPassStep(String message) {
        ExtentReportManager.logPass(message);
    }

    protected void logFailStep(String message) {
        ExtentReportManager.logFail(message);
    }

    protected void logWarningStep(String message) {
        ExtentReportManager.logWarning(message);
    }

    protected void AssertFail(boolean bool, String message){
        if(bool){
            logPassStep(message);
        }else{
            logFailStep(message);
            Assert.fail(message);
        }
    }

    protected void AssertFailAndContinue(boolean bool, String message){
        if(bool){
            logPassStep(message);
        }else{
            logFailStep(message);
        }
    }

    protected void navigateBack(){
        staticWait(2000);
        getDriver().navigate().back();
    }

    protected void staticWait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String generateUniquePhoneNumber() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

}