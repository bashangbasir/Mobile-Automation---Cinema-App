package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import static org.testng.Assert.*;

public class MovieDetailsPage extends BasePage{

    public MovieDetailsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    // ELEMENTS

    @AndroidFindBy(accessibility = "Navigate up")
    public MobileElement backBtn;

    @AndroidFindBy(id = "com.tengyeekong.movieapp:id/ivPoster")
    public MobileElement moviePoster;

    @AndroidFindBy(id = "com.tengyeekong.movieapp:id/collapsingToolbar")
    public MobileElement movieDetailsFrame;

    @AndroidFindBy(id = "com.tengyeekong.movieapp:id/tvSynopsis")
    public MobileElement movieSynopsis;

    @AndroidFindBy(id = "com.tengyeekong.movieapp:id/btnBookMovie")
    public MobileElement bookMovieBtn;

    //ACTIONS

    public MovieDetailsPage isOnMovieDetailsPage() {
        assertTrue(waitForElement(backBtn).isDisplayed());
        assertTrue(waitForElement(moviePoster).isDisplayed());
        assertTrue(waitForElement(movieDetailsFrame).isDisplayed());
        assertTrue(waitForElement(bookMovieBtn).isDisplayed());
        return this;
    }

    public BookMoviePage clickBookMovieBtn() {
        bookMovieBtn.click();
        return new BookMoviePage(driver);
    }

    public void clickBackBtn() {
        backBtn.click();
    }

    public MovieDetailsPage scrollUp() {
        new TouchAction(this.driver)
                .longPress(ElementOption.element(bookMovieBtn))
                .moveTo(PointOption.point(screenWidth/2,(int) (screenHeight * 0.2)))
                .release().perform();
        return this;
    }

    public MovieDetailsPage verifyMoviePosterIsHidden() {
        boolean isMoviePosterPresent = isElementPresent(MobileBy.id("com.tengyeekong.movieapp:id/ivPoster"));
        assertFalse(isMoviePosterPresent);
        return this;
    }

    public MovieDetailsPage verifyMovieTitle(String expectedTitle){
        String actualTitle = movieDetailsFrame.getAttribute("content-desc");
        assertEquals(actualTitle, expectedTitle);
        return this;
    }



}
