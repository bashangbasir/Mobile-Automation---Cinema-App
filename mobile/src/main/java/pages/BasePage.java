package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    AppiumDriver driver;
    public static final long WAIT = 10;
    public int screenWidth;
    public int screenHeight;

    public BasePage(AppiumDriver appiumDriver){
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
        this.screenHeight = driver.manage().window().getSize().getHeight();
        this.screenWidth = this.driver.manage().window().getSize().getWidth();
    }

    public boolean isElementPresent(By locator) {
        List<MobileElement> elementList = driver.findElements(locator);
        if(elementList.size() == 0) {
            return false;
        }else{
            return true;
        }
    }

    public MobileElement waitForElement(MobileElement el) {
        WebDriverWait wait = (WebDriverWait)(new WebDriverWait(driver, 30L)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(el));
        return el;
    }


}
