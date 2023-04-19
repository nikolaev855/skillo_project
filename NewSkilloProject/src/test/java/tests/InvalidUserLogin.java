package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InvalidUserLogin {
    private WebDriver driver;
    @BeforeSuite
    public void setupSuite(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setupDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DataProvider(name = "users")
    public Object[][] getUsers(){
        return new Object[][]{
                {"greshen13", "auto_pass"}, //Enter auto_user in order to test the assert
        };

    }
    @Test(dataProvider = "users")
    public void loginTest(String usernameOrEmail, String password){
        System.out.println("1.Navigate to homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();


        System.out.println("2. Navigate to Login");
        Header header = new Header(driver);
        header.goToLogin();

        System.out.println("3. Check the correct url is opened");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();

        System.out.println("4. Check that the sign in header is displayed");
        String headerText = loginPage.getSignInHeaderText();
        Assert.assertEquals(headerText, "Sign in", "Incorrect Sign in Header Text!");

        System.out.println("5. enter invalid username");
        loginPage.enterUsername(usernameOrEmail);

        System.out.println("6. Enter password");
        loginPage.enterPassword(password);

        System.out.println("7. Click sign in button");
        loginPage.clickSignIn();

        System.out.println("8.Verify that an error appears");
        loginPage.getErrorText();
        Assert.assertEquals(loginPage.getErrorText(), "User not found");
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.close();
        }
    }
}
