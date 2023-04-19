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

import java.time.Duration;

public class RegisterTest {
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DataProvider(name = "users")
    public Object[][] getUsers(){
        return new Object[][]{
                {"sluchaen29", "sluchaen29@abv.bg", "parola123"},
        };

    }
    @Test(dataProvider = "users")
    public void registerTest(String username,String email, String password){
        System.out.println("1.Navigate to homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();


        System.out.println("2. Navigate to Login");
        Header header = new Header(driver);
        header.goToLogin();

        System.out.println("2. Navigate to Register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.goToRegister();

        System.out.println("3. Verify that the register url is opened");
        registerPage.verifyUrl();

        System.out.println("4. Check that the sign up header is displayed");
        String registerText = registerPage.getRegisterHeaderText();
        Assert.assertEquals(registerText, "Sign up", "Incorrect Sign up Header Text!");


        System.out.println("5. Enter username");
        registerPage.enterUsername(username);

        System.out.println("6. Enter email");
        registerPage.enterEmail(email);

        System.out.println("6. Enter password");
        registerPage.enterPassword(password);

        System.out.println("6. Enter password");
        registerPage.confirmPassword(password);

        System.out.println("7. Click sign in button");
        registerPage.clickSignIn();

       System.out.println("8.Verify URL");
        homePage.verifyUrl();

        System.out.println("9.Navigate to profile page");
        header.goToProfile();

        System.out.println("10. Verify the url");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();

        System.out.println("11. Verify the username");
        String usernameHeaderText = profilePage.getUsernameHeaderText();
        Assert.assertEquals(usernameHeaderText, username, "Incorrect username!");

    }



    //napravi taka che da pravi screenshot pri greshka v asserta, kakto tuk taka i v prednite testove + napravi oshte 2 testa (BaseTest ne se broi za test)
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.close();
        }
    }
}
