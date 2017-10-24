import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest {
    WebDriver driver;

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
    }

    @Test
    public void firstTest(){
        driver.get("http://google.com");
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
