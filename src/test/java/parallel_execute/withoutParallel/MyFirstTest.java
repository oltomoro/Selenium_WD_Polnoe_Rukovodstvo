package parallel_execute.withoutParallel;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @Test
    public void mySecondTest() {
        driver.navigate().to("http://www.google.com");
        wait.until((WebDriver d) -> d.findElement(By.name("q"))).sendKeys("webdriver" + Keys.ENTER);
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @Test
    public void myThirdTest() {
        driver.navigate().to("http://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement q = driver.findElement(By.name("q"));
        /*driver.findElement(By.id("gs_ok0")).click();
        driver.findElement(By.id("K32")).click();*/
        q.sendKeys("webdriver" + Keys.ENTER);
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
