package tests;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class sample extends BaseTest {
    @Test
    public void sampleTest() throws InterruptedException {
        System.out.println("\nDONE........");
        Thread.sleep(2000);
        AssertFailAndContinue(false, "HomePage Opened");
        System.out.println("next step");
    }

}
