package pages;

import com.google.common.collect.Ordering;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MovieListingPage extends BasePage{

    public MovieListingPage(AppiumDriver appiumDriver){
        super(appiumDriver);
    }


    //ELEMENTS
    @AndroidFindBy(xpath = "*//android.view.ViewGroup[@resource-id='com.tengyeekong.movieapp:id/toolbar']/android.widget.TextView")
    public MobileElement movieAppTitle;

    @AndroidFindBy(accessibility = "Sort")
    public MobileElement sortOptionButton;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.tengyeekong.movieapp:id/recyclerView']/android.widget.FrameLayout")
    public List<MobileElement> movieCards;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sort by\")")
    public MobileElement sortBy;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Release date\")")
    public MobileElement releaseDate;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Title\")")
    public MobileElement title;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Popularity\")")
    public MobileElement popularity;


    //ACTIONS

    public MovieListingPage isOnMovieListingPage() {
        assertTrue(waitForElement(movieAppTitle).isDisplayed());
        assertEquals(movieAppTitle.getText(), "Movie App");
        assertTrue(waitForElement(sortOptionButton).isDisplayed());
        for (int i=0; i<movieCards.size();i++){
            assertTrue(waitForElement(movieCards.get(i)).isDisplayed());
        }
        return this;
    }

    public MovieDetailsPage clickFirstMovieList() {
        movieCards.get(0).click();
        return new MovieDetailsPage(driver);
    }

    public MovieListingPage clickSortOptionButton() {
        sortOptionButton.click();
        return this;
    }

    public MovieListingPage verifySortOptionPopUp() {
        assertTrue(sortBy.isDisplayed());
        assertTrue(releaseDate.isDisplayed());
        assertTrue(title.isDisplayed());
        assertTrue(popularity.isDisplayed());
        return  this;
    }

    public MovieListingPage clickTitleSortBy() {
        title.click();
        return this;
    }

    public MovieListingPage clickReleaseDateSortBy() {
        releaseDate.click();
        return this;
    }

    public MovieListingPage clickPopularitySortBy() {
        popularity.click();
        return this;
    }

    public String getMovieTitle(MobileElement el) {
        String movieTitle = el.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.tengyeekong.movieapp:id/tvTitle']")).getText();
        return movieTitle;
    }

    public MovieListingPage closeSortByPopUp(){
        driver.navigate().back();
        return this;
    }

    public MovieListingPage verifyPopularityOrder(String order) {
        List<Float> popularityValues = new ArrayList<>();
        for(int i = 0; i < movieCards.size()-1; i++){
            MobileElement el = movieCards.get(i).findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.tengyeekong.movieapp:id/tvPopularity']"));
            popularityValues.add(Float.valueOf(el.getText().replace("Popularity: ","")));
        }
        if(order.equalsIgnoreCase("asc")){
            assertTrue(popularityValues.get(0)<=popularityValues.get(1), "The order is not ascending. [" + popularityValues.get(0) + "," + popularityValues.get(1) +"] instead");
        }else if(order.equalsIgnoreCase("desc")){
            assertTrue(popularityValues.get(0)>=popularityValues.get(1), "The order is not desscending. [" + popularityValues.get(0) + "," + popularityValues.get(1) +"] instead");
        }
        return this;
    }

    public MovieListingPage verifyTitleOrder(String order) {
        List<String> titleValues = new ArrayList<>();
        for(int i = 0; i < movieCards.size()-1; i++){
            String title = getMovieTitle(movieCards.get(i));
            titleValues.add(title);
        }
        boolean isSorted = Ordering.natural().isOrdered(titleValues);
        if(order.equalsIgnoreCase("asc")){
            assertTrue(isSorted, "The order is not ascending. [" + titleValues + "]");
        }else if(order.equalsIgnoreCase("desc")){
            assertFalse(isSorted, "The order is not descending. [" + titleValues + "]");
        }
        return this;
    }


}
