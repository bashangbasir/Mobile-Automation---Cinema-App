
import org.testng.annotations.Test;
import pages.BookMoviePage;
import pages.MovieDetailsPage;
import pages.MovieListingPage;

public class MovieAppTest extends BaseTest {

    MovieListingPage movieListingPage;
    MovieDetailsPage movieDetailsPage;
    BookMoviePage bookMoviePage;

    @Test
    public void BookMovieAppTest(){

        movieListingPage = new MovieListingPage(driver);

        String movieTitle = movieListingPage
                .isOnMovieListingPage()
                .getMovieTitle(movieListingPage.movieCards.get(0));

        movieDetailsPage = movieListingPage
                .clickFirstMovieList();

        bookMoviePage = movieDetailsPage
                .isOnMovieDetailsPage()
                .verifyMovieTitle(movieTitle)
                .scrollUp()
                .verifyMoviePosterIsHidden()
                .clickBookMovieBtn();

        bookMoviePage
                .isOnBookMoviePage();
    }

    @Test
    public void SortMovieAppByPopularityAscTest() {

        movieListingPage = new MovieListingPage(driver);

        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .clickPopularitySortBy()
                .closeSortByPopUp()
                .verifyPopularityOrder("asc");
    }

    @Test
    public void SortMovieAppByPopularityDescTest() {

        movieListingPage = new MovieListingPage(driver);

        //need to click popularity two times to make it desc
        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .clickPopularitySortBy()
                .clickPopularitySortBy()
                .closeSortByPopUp()
                .verifyPopularityOrder("desc");
    }

    @Test
    public void SortMovieAppByReleaseDateAscTest() throws Exception {

        movieListingPage = new MovieListingPage(driver);


        //by default desc release date
        //TODO -> need to check how to verify the order of release date
        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .clickReleaseDateSortBy()
                .closeSortByPopUp();

        //throw new Exception("Tests case is not done!");
    }
    @Test
    public void SortMovieAppByReleaseDateDescTest() throws Exception {

        movieListingPage = new MovieListingPage(driver);

        //by default desc release date
        //TODO -> need to check how to verify the order of release date
        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .closeSortByPopUp();

        //throw new Exception("Tests case is not done!");

    }

    @Test
    public void SortMovieAppByTitleAscTest() {

        movieListingPage = new MovieListingPage(driver);

        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .clickTitleSortBy()
                .closeSortByPopUp()
                .verifyTitleOrder("asc");
    }

    @Test
    public void SortMovieAppByTitleDescTest() {

        movieListingPage = new MovieListingPage(driver);

        //need to click popularity two times to make it desc
        movieListingPage
                .isOnMovieListingPage()
                .clickSortOptionButton()
                .verifySortOptionPopUp()
                .clickTitleSortBy()
                .clickTitleSortBy()
                .closeSortByPopUp()
                .verifyTitleOrder("desc");
    }


}
