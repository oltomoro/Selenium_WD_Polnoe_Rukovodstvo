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

public class Task8CheckAllStickers {
    WebDriver driver;
    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkAllStickers(){
        // открываем главную страницу
        //---------------------------------
        driver.navigate().to("http://j763976.myjino.ru/");

        //  получаем локаторы
        //-----------------------------------
        String locProduct = "//li[contains(@class, 'product')]"; // локатор товаров
        String locSticker = ".//div[contains(@class, 'sticker')]"; // локатор стикеров

        //  проверяем наличие стикеров
        //-----------------------------------
        List<WebElement> productsList = driver.findElements(By.xpath(locProduct)); // получаем список товаров
        for (int i = 0; i < productsList.size(); i++) {
            WebElement product = productsList.get(i); // получаем текущий товар
            List<WebElement> stickersList = product.findElements(By.xpath(locSticker)); // получаем все стикеры у текущего товара

            // проверяем, что стикер у товара только один
            Assert.assertTrue(stickersList.size() == 1);
        }
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
