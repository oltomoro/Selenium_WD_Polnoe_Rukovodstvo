import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Attributes {
    WebDriver driver;
    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkAllStickers(){
        driver.navigate().to("http://j763976.myjino.ru/");
        WebElement el = driver.findElement(By.xpath("//input[@name='query']"));
        el.sendKeys("ololo");
        String text = el.getText();
        String value = el.getAttribute("value");

        WebElement el2 = driver.findElement(By.xpath("//*[@id='breadcrumbs']//a"));
        String value2 = el2.getAttribute("href");
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
