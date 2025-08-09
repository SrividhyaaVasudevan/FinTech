package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String reportPath;
    private static String screenshotPath;

    /**
     * Set WebDriver instance for the current thread
     */
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    /**
     * Get WebDriver instance for the current thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Remove WebDriver from ThreadLocal
     */
    public static void removeDriver() {
        driver.remove();
    }

    /**
     * Initialize ExtentReports with configuration
     */
    public static void initReports() {
        if (extent == null) {
            // Create timestamp for unique report names
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            // Setup report and screenshot directories
            String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports/";
            String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";

            // Create directories if they don't exist
            new File(reportDir).mkdirs();
            new File(screenshotDir).mkdirs();

            reportPath = reportDir + "ExtentReport_" + timestamp + ".html";
            screenshotPath = screenshotDir;

            // Initialize Spark Reporter
            sparkReporter = new ExtentSparkReporter(reportPath);

            // Configure Spark Reporter
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set system information
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
        }
    }

    /**
     * Create a new test in the report
     */
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }

    /**
     * Create a new test with category
     */
    public static void createTest(String testName, String description, String category) {
        ExtentTest extentTest = extent.createTest(testName, description);
        extentTest.assignCategory(category);
        test.set(extentTest);
    }

    /**
     * Log info step with screenshot (no driver parameter needed)
     */
    public static void logInfo(String message) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().info(message);
        }
    }

    /**
     * Log info step without screenshot
     */
    public static void logInfoOnly(String message) {
        test.get().info(message);
    }

    /**
     * Log pass step with screenshot (no driver parameter needed)
     */
    public static void logPass(String message) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
        }
    }

    /**
     * Log fail step with screenshot (no driver parameter needed)
     */
    public static void logFail(String message) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
        }
    }

    /**
     * Log fail step with exception and screenshot (no driver parameter needed)
     */
    public static void logFail(String message, Throwable throwable) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().fail(throwable, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().fail(throwable);
        }
    }

    /**
     * Log warning step with screenshot (no driver parameter needed)
     */
    public static void logWarning(String message) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().warning(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().warning(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
        }
    }

    /**
     * Log skip step
     */
    public static void logSkip(String message) {
        test.get().skip(MarkupHelper.createLabel(message, ExtentColor.ORANGE));
    }

    /**
     * Capture screenshot and return as Base64 string
     */
    private static String captureScreenshot(WebDriver driver) {
        try {
            if (driver != null && driver instanceof TakesScreenshot) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
        return null;
    }

    /**
     * Capture screenshot and save to file (no driver parameter needed)
     */
    public static String captureScreenshotToFile(String testName) {
        return captureScreenshotToFile(getDriver(), testName);
    }
    public static String captureScreenshotToFile(WebDriver driver, String testName) {
        try {
            if (driver != null && driver instanceof TakesScreenshot) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
                String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
                String filePath = screenshotPath + fileName;

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File(filePath));

                return filePath;
            }
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
        return null;
    }

    /**
     * Add screenshot from file path
     */
    public static void addScreenshotFromPath(String message, String screenshotPath) {
        try {
            test.get().info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            test.get().info(message + " (Screenshot could not be attached)");
        }
    }

    /**
     * Log custom step with specific status and screenshot (no driver parameter needed)
     */
    public static void logStep(Status status, String message) {
        String screenshotBase64 = captureScreenshot(getDriver());
        if (screenshotBase64 != null) {
            test.get().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else {
            test.get().log(status, message);
        }
    }

    /**
     * Create child test (for data-driven tests)
     */
    public static void createChildTest(String childTestName, String description) {
        ExtentTest childTest = test.get().createNode(childTestName, description);
        test.set(childTest);
    }

    /**
     * Flush the reports (call this at the end of test execution)
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Get the current test instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Alternative method signatures for colored text with screenshots
     */
    public static void logPassWithColor(String message, WebDriver driver) {
        String screenshotBase64 = captureScreenshot(driver);
        ExtentTest currentTest = test.get();

        if (screenshotBase64 != null) {
            currentTest.pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
            currentTest.addScreenCaptureFromBase64String(screenshotBase64);
        } else {
            currentTest.pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
        }
    }

    public static void logFailWithColor(String message, WebDriver driver) {
        String screenshotBase64 = captureScreenshot(driver);
        ExtentTest currentTest = test.get();

        if (screenshotBase64 != null) {
            currentTest.fail(MarkupHelper.createLabel(message, ExtentColor.RED));
            currentTest.addScreenCaptureFromBase64String(screenshotBase64);
        } else {
            currentTest.fail(MarkupHelper.createLabel(message, ExtentColor.RED));
        }
    }

    public static void logWarningWithColor(String message, WebDriver driver) {
        String screenshotBase64 = captureScreenshot(driver);
        ExtentTest currentTest = test.get();

        if (screenshotBase64 != null) {
            currentTest.warning(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
            currentTest.addScreenCaptureFromBase64String(screenshotBase64);
        } else {
            currentTest.warning(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
        }
    }

    /**
     * Remove the current test from ThreadLocal
     */
    public static void removeTest() {
        test.remove();
    }
    /**
     * Get report file path
     */
    public static String getReportPath() {
        return reportPath;
    }
}
