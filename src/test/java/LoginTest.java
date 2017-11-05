import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LoginTest {
    public WebDriver driver;

    @Before
    public void beforeTest(){
        //driver = new ChromeDriver();
        driver = new InternetExplorerDriver();
    }

    @Test
    public void loginTest(){
        driver.navigate().to("http://j763976.myjino.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); // вводим логин
        driver.findElement(By.name("password")).sendKeys("ololo_admin"); // вводим пароль
        driver.findElement(By.name("login")).click(); // жмем кнопку входа
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
