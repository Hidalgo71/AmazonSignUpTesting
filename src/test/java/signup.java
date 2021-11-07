import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
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
        amzDriver.findElement(By.id("sp-cc-accept")).click();                                           //Accept Cookies

        Actions amzAct = new Actions(amzDriver);
        amzAct.moveToElement(amzDriver.findElement(By.id("nav-link-accountList"))).build().perform();   //Mouse over for signin btn
        expW.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-flyout-accountList")));
        WebElement cursorMove = amzDriver.findElement(By.className("nav-a"));
        amzAct.moveToElement(amzDriver.findElement(By.className("nav-a"))).click().build().perform();
        //amzDriver.f



    }
}
