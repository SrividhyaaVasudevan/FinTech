package pages.repo;

import common.UiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageRepo extends UiBase {

    @FindBy(xpath = "//input[@name=\"username\"]")
    protected WebElement usernameField;

    @FindBy(xpath = "//input[@name=\"password\"]")
    protected WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]//b[contains(text(),\"User\")]")
    protected WebElement usernameLabel;

    @FindBy(xpath = "//input[@name=\"password\"]")
    protected WebElement passwordLabel;

    @FindBy(xpath = "//input[@value=\"Log In\"]")
    protected WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"rightPanel\"]/p")
    protected WebElement errorMsg;

    @FindBy(xpath = "//a[text()=\"Register\"]")
    protected WebElement registerBtn;

    @FindBy(xpath = "//*[@id=\"customer.firstName\"]")
    protected WebElement firstName;

    @FindBy(xpath = "//*[@id=\"customer.lastName\"]")
    protected WebElement lastName;

    @FindBy(xpath = "//*[@id=\"customer.address.street\"]")
    protected WebElement streetField;

    @FindBy(xpath = "//*[@id=\"customer.address.city\"]")
    protected WebElement cityField;

    @FindBy(xpath = "//*[@id=\"customer.address.state\"]")
    protected WebElement stateField;

    @FindBy(xpath = "//*[@id=\"customer.address.zipCode\"]")
    protected WebElement zipcodeField;

    @FindBy(xpath = "//*[@id=\"customer.phoneNumber\"]")
    protected WebElement phoneField;

    @FindBy(xpath = "//*[@id=\"customer.ssn\"]")
    protected WebElement sssField;

    @FindBy(xpath = "//*[@id=\"customer.username\"]")
    protected WebElement usernameRegisterField;

    @FindBy(xpath = "//*[@id=\"customer.password\"]")
    protected WebElement passwordRegisterField;

    @FindBy(xpath = "//*[@id=\"repeatedPassword\"]")
    protected WebElement confirmPassword;

    @FindBy(xpath = "//input[@value=\"Register\"]")
    protected WebElement registerButton;

    @FindBy(xpath = "//*[@id=\"rightPanel\"]/p[contains(text(),\"created\")]")
    protected WebElement accountCreatedMsg;


}
