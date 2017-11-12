import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookiesTest {
    WebDriver driver;

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
    }

    @Test
    public void testCookie(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
