import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static AppiumDriver driver;

    @BeforeMethod
    public static void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Android desired capabilities setup
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("deviceName", "Nexus 6");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/main/resources/movie-app-release.apk");
        capabilities.setCapability("appPackage","com.tengyeekong.movieapp");
        capabilities.setCapability("appActivity", "com.tengyeekong.movieapp.movielist.MovieListActivity");
        //create driver
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        if (null != this.driver) {
            this.driver.quit();
        }
    }

}
