/* import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NewTestJava {
    ChromeDriver driver;
    final String baseUrl = "http://training.skillo-bg.com:4200";
    final String registerUrl = baseUrl + "/users/register";
    final String homeUrl = baseUrl + "/posts/all";
    @BeforeMethod
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3
        ));
        driver.get(homeUrl);
    }
    @Parameters({"username", "email", "password"})
    @Test
    public void registerUser(String username, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("1. Navigate to Login page");
             WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-login")));
        loginButton.click();
        clickElement(By.id("nav-link-login"), 5, 100);

        System.out.println("2. Click on 'register'");
        clickElement(By.linkText("Register"), 5);

        System.out.println("3. Validate that url hsa changed to /register");
        wait.until(ExpectedConditions.urlToBe(registerUrl));

        System.out.println("4. Validate that the Sign up header is shown");
        WebElement signUpHeader = driver.findElement(By.xpath("//app-register//h4[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUpHeader));

        System.out.println("5. Enter username");
     WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("AutoUser_855");

        Boolean isValid = usernameField.getAttribute("class").contains("is-valid");
        Assert.assertTrue(isValid, "The field is invalid!");
        WebElement usernameField = driver.findElement(By.name("username"));
        enterTextField(usernameField, username);

        System.out.println("6. Enter email");
        WebElement emailField = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input"));
        enterTextField(emailField, email);


        System.out.println("7. Enter password");
        WebElement passField = driver.findElement(By.id("defaultRegisterFormPassword"));
        enterTextField(passField, password);

        System.out.println("8. Confirm password");
        WebElement confirmPassField = driver.findElement(By.id("defaultRegisterPhonePassword"));
        enterTextField(confirmPassField, password);

        System.out.println("9. Click Sign in button");
        clickElement(By.id("sign-in-button"), 3);

        System.out.println("10. Validate that a pop-up appears indicating a successfull registration");
        WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        String toastMsgText = toastMsg.getText().trim();
        Assert.assertEquals(toastMsgText, "Successful register!", "Registration was not successful");

        System.out.println("11.Validate that the user is redirected to homepage");
        wait.until(ExpectedConditions.urlToBe(homeUrl));
    }
@Parameters({"username", "password"})
    @Test(dependsOnMethods = "registerUser")
    public void testLogin(String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("1. Navigate to login page by clicking Login tab button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("2. Validate that the url is correct");
        String expectedUrl = "http://training.skillo-bg.com:4200/users/login";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        System.out.println("3. Validate that the 'sign in' text is visible");
        WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInElement.isDisplayed(),"Sign in form is not visible");

        System.out.println("4. Enter correct username");
        WebElement usernameField = driver.findElement(By.name("usernameOrEmail"));
        usernameField.sendKeys(username);

        System.out.println("5. Enter correct password");
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        System.out.println("6. Click Sign in button");
        WebElement signInButton = driver.findElement(By.cssSelector("#sign-in-button"));
        signInButton.click();

        System.out.println("7. Validate that the url is correct (Home page expected");
        wait.until(ExpectedConditions.urlToBe(homeUrl));

        System.out.println("8. Validate that there is a Profile tab button visible");
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        smallWait.until(ExpectedConditions.visibilityOf(profileLink));

        System.out.println("9. Validate that there is a New Post tab button visible");
        WebElement newPostLink = driver.findElement(By.linkText("New post"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newPostLink.isDisplayed(),"New post link is not visible");

        System.out.println("10. Click profile button");
        profileLink.click();

        System.out.println("11. Validate that the correct username is displayed");
        WebElement usernameHeader = driver.findElement(By.cssSelector("app-profile-section h2"));
        softAssert.assertEquals(usernameHeader.getText(),username, "Incorrect username!");
        softAssert.assertAll();

    }

    private WebElement clickElement(By locator, int timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;
    }

    private WebElement clickElement(By locator, int totalTimeoutSec, int retryTimeotMs){
        FluentWait<ChromeDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(retryTimeotMs))
                .ignoring(TimeoutException.class);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
       return element;

    }

    private void enterTextField(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
        Boolean isValid = element.getAttribute("class").contains("is-valid");
        Assert.assertTrue(isValid, "The field is invalid!");
    }
    @AfterMethod
    public void teardown(){
        driver.close();
    }
}
 */
