import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Test1 - DrugAndDrop вручную через Actions
 * Test2 - DrugAndDrop через Actions().dragAndDrop()
 * Test3 - работа с выпадающими меню
 */

public class SeleniumActionsTest2 {
    WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.navigate().to("http://jqueryui.com/resources/demos/sortable/connect-lists.html");

        // делаем drag n drop через Actions
        WebElement el1 = driver.findElement(By.xpath("//ul[@id='sortable2']/li[1]"));
        WebElement el2 = driver.findElement(By.xpath("//ul[@id='sortable2']/li[2]"));

        new Actions(driver)
            .moveToElement(el1)
            .clickAndHold()
            .moveToElement(el2, 1, 25)
            .release()
            .perform();

        /*Actions action = new Actions(driver);
        action.moveToElement(el1);
        action.clickAndHold();
        //action.moveToElement(el2);
        action.moveToElement(el2,1,25);
        action.release();
        action.perform();*/

        /*new Actions(driver)
            .dragAndDrop(el1, el2);*/
    }

    @Test
    public void test2() throws InterruptedException {
        driver.navigate().to("http://jqueryui.com/resources/demos/sortable/connect-lists.html");

        // делаем drag n drop через Actions.dragAndDrop
        WebElement el1 = driver.findElement(By.xpath("//ul[@id='sortable2']/li[1]"));
        WebElement el2 = driver.findElement(By.xpath("//ul[@id='sortable2']/li[2]"));

        new Actions(driver)
            .dragAndDrop(el1, el2)
            .perform();
    }

    @Test
    public void test3() {
        driver.navigate().to("http://demo.instantcms.ru/");

        // формируем локаторы
        WebElement menuArticles = driver.findElement(By.xpath("//*[@id='widget_pos_top']//ul[@class='menu']//*[contains(text(),'Статьи')]"));
        WebElement menuEl1 = driver.findElement(By.xpath("//*[@id='widget_pos_top']//ul//*[contains(text(),'Литература')]"));
        WebElement menuEl2 = driver.findElement(By.xpath("//*[@id='widget_pos_top']//ul//*[contains(text(),'Отечественная')]"));

        // жмем на элемент меню
        new Actions(driver)
            .moveToElement(menuArticles)
            .moveToElement(menuEl1)
            .moveToElement(menuEl2)
            .click()
            .perform();

    }


    @After
    public void after() {
        //driver.quit();
    }
}
