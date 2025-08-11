package tests;

import common.BaseTest;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Map;

public class UIValidationTest extends BaseTest {

    @Test
    public void verify_form_alignment() {
        WebElement username = loginPageAction.getUserNameElement();
        WebElement password = loginPageAction.getPasswordElement();

        Point usernameLoc = username.getLocation();
        Point passwordLoc = password.getLocation();

        // Example alignment check: fields should be vertically aligned
        AssertFail(usernameLoc.getX() == passwordLoc.getX(),"Alignment is correct");
    }

    @Test
    public void verify_label_association() {
        // In ideal cases, <label for="inputId"> is used
        WebElement usernameLabel = loginPageAction.getUserNameLabelElement();
        WebElement passwordLabel = loginPageAction.getPasswordLabelElement();

        AssertFail(usernameLabel.isDisplayed(), "Username Label is displayed");
        AssertFail(passwordLabel.isDisplayed(), "Password Label is displayed");
    }

    @Test
    public void verify_font_and_theme() {
        String fontFamily = loginPageAction.getUserNameLabelElement().getCssValue("font-family");
        AssertFail(fontFamily.toLowerCase().contains("arial") || fontFamily.toLowerCase().contains("sans-serif"),"Font Verification");
    }

    @Test
    public void verify_layout_responsiveness() {
        loginPageAction.responsiveTest();
    }



}
