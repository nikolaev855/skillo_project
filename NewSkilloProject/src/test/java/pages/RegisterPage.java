package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
public class RegisterPage extends BasePage {

    public static String URL = "http://training.skillo-bg.com:4200/users/register";
    WebDriverWait wait;

    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/h4")
    WebElement registerHeader;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input")
    WebElement emailField;
    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(name = "verify-password")
    WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-login/div/div/form/p[2]/a")
    WebElement registerLink;

    public RegisterPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void goToRegister(){
        clickElement(registerLink);
    }
    public void enterUsername(String username){
        // wait.until(ExpectedConditions.visibilityOf(usernameField));
        //usernameField.sendKeys(username);
        enterText(usernameField, username);
    }
    public void enterEmail(String email){
        // wait.until(ExpectedConditions.visibilityOf(usernameField));
        //usernameField.sendKeys(username);
        enterText(emailField, email);
    }

    public void enterPassword(String password){
        enterText(passwordField, password);

    }
    public void confirmPassword(String password){
        enterText(confirmPasswordField, password);

    }
    public void clickSignIn(){
        clickElement(signInButton);
    }
//    public void goToRegister(){
//        clickElement(reg);
//    }

    public String getRegisterHeaderText(){ return getElementText(registerHeader);}

    public void verifyUrl() {verifyUrl(URL);}
    public void register(String username, String email, String password){
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

}
