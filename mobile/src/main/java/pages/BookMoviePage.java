package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;

import static org.testng.Assert.*;

public class BookMoviePage extends BasePage{


    public BookMoviePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    //ELEMENTS

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    public MobileElement backBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.tengyeekong.movieapp:id/toolbar']/android.widget.TextView")
    public MobileElement pageTitle;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(accessibility = "Sign in")
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign in\")")
    public MobileElement signInBtn;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='hplogoo']")
    public MobileElement googleLogo;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='tsf']")
    public MobileElement googleSearchBar;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='footer']")
    public MobileElement googleFooter;

    // Actions

    public BookMoviePage isOnBookMoviePage() {
        assertTrue(waitForElement(backBtn).isDisplayed());
        assertTrue(waitForElement(pageTitle).isDisplayed());
        assertTrue(waitForElement(signInBtn).isDisplayed());
        assertTrue(waitForElement(googleLogo).isDisplayed());
        assertTrue(waitForElement(signInBtn).isDisplayed());
        assertTrue(waitForElement(googleFooter).isDisplayed());
        assertEquals(waitForElement(pageTitle).getText(), "Book the movie");
        return this;
    }

    public void clickBackBtn() {
        backBtn.click();
    }

}
