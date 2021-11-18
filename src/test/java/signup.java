import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class signup
{
    public static void main(String[] args) throws IOException
    {
        DesiredCapabilities chDCap = DesiredCapabilities.chrome();
        chDCap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chDCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.chrome.driver", "D:\\Docs\\Drivers\\chromedriver.exe");
        WebDriver amzDriver = new ChromeDriver();

        Logger signUpLog = Logger.getLogger("sUpLog");
        FileHandler fileH;
        fileH = new FileHandler("C:\\Users\\Hidalgo\\Desktop\\signUpLog.log");
        signUpLog.addHandler(fileH);
        SimpleFormatter smpFormatter = new SimpleFormatter();
        fileH.setFormatter(smpFormatter);
        signUpLog.info("Log File Created.");

        amzDriver.get("https://www.amazon.com.tr");
        amzDriver.manage().deleteAllCookies();
        signUpLog.info("Page Opened, cookies deleted");

        WebDriverWait expW = new WebDriverWait(amzDriver, 3);
        amzDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        amzDriver.findElement(By.id("sp-cc-accept")).click();                                           //Accept Cookies

        Actions amzAct = new Actions(amzDriver);
        amzAct.moveToElement(amzDriver.findElement(By.id("nav-link-accountList"))).build().perform();   //Mouse over for signin btn
        WebElement cursorMove = amzDriver.findElement(By.className("nav-a"));
        expW.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-flyout-accountList")));
        amzAct.moveToElement(amzDriver.findElement(By.xpath("//*[@id=\"nav-flyout-ya-newCust\"]/a"))).click().build().perform();
        signUpLog.info("Sign Up button clicked.");

        Set<String> parWin = amzDriver.getWindowHandles();
        Set<String> s = amzDriver.getWindowHandles();
        for (String chWindow : s)
        {
            if (!parWin.equals(chWindow))
            {
                amzDriver.switchTo().window(chWindow);
            }
        }
        signUpLog.info("Switched new window.");
        //expW.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_customer_name")));

        amzDriver.findElement(By.id("ap_customer_name")).sendKeys("Kobe Bryant");
        amzDriver.findElement(By.id("ap_email")).sendKeys("@mail.com");
        amzDriver.findElement(By.id("ap_password")).sendKeys("");
        amzDriver.findElement(By.id("ap_password_check")).sendKeys("");
        signUpLog.info("User info entered");

        String signTitle = amzDriver.getTitle();
        amzDriver.findElement(By.id("continue")).click();
        for (String chWindow : s)
        {
            if (!parWin.equals(chWindow))
            {
                amzDriver.switchTo().window(chWindow);
            }
        }
        String testTitle = amzDriver.getTitle();
        if (Objects.equals(signTitle, testTitle))
        {
            System.out.println("Test Failed.");
            System.out.println(amzDriver.findElement(By.xpath("//*[@id=\"auth-password-mismatch-alert\"]/div/div")).getText());
            signUpLog.info("Test Failed.");
        }
        else
        {
            System.out.println("Test Completed successfully.");
            signUpLog.info("Test Completed successfully");

        }
    }
}
