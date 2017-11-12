import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task7ClickAllSectionsAdmin {
    WebDriver driver;

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void clickAllSections() {
        // авторизация
        driver.navigate().to("http://j763976.myjino.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); // вводим логин
        driver.findElement(By.name("password")).sendKeys("ololo_admin"); // вводим пароль
        driver.findElement(By.name("login")).click(); // жмем кнопку входа


        //  получаем локаторы
        //-----------------------------------
        String locMenuItemsListParents = "//ul[@id='box-apps-menu']/li"; // локатор родительских элементов меню
        String locMenuItemsListChild = ".//ul[@class='docs']//a"; // локатор дочерних элементов меню
        String locHeader = "//td[@id='content']/h1"; // локатор заголовка

        //  прокликиваем по всем элементам
        //-----------------------------------
        List<WebElement> menuItemsListParents = driver.findElements(By.xpath(locMenuItemsListParents)); // получаем список родительских элементов
        for (int i = 0; i < menuItemsListParents.size(); i++) {
            WebElement elParentItem = driver.findElements(By.xpath(locMenuItemsListParents)).get(i); // находим нужный элемент
            elParentItem.click(); // кликаем по нему

            List<WebElement> menuItemsListChilds = driver.findElements(By.xpath(locMenuItemsListChild)); // получаем список дочерних элементов
            // если есть дочерние элементы
            if (menuItemsListChilds.size() > 0) {
                for (int j = 1; j < menuItemsListChilds.size(); j++) {
                    WebElement elChildItem = driver.findElements(By.xpath(locMenuItemsListChild)).get(j); // находим нужный элемент
                    elChildItem.click(); // кликаем по нему

                    // проверяем присутствует ли header на открытой странице
                    Assert.assertTrue(isElementPresent(locHeader));
                }
            }else{
                // проверяем присутствует ли header на открытой странице
                Assert.assertTrue(isElementPresent(locHeader));
            }

        }
    }



    /*public WebElement elemIsPresent(WebDriver driver, String elXpath) {
        return (new WebDriverWait(driver, 3))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elXpath)));
    }*/



    public Boolean isElementPresent(String xpath) {
        List<WebElement> elementsList = driver.findElements(By.xpath(xpath));
        return elementsList.size() > 0;
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
