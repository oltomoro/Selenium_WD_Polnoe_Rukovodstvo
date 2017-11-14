import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task9SortingCountries {
    WebDriver driver;

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void sortingCountries(){
        // авторизация
        //--------------------------
        driver.navigate().to("http://j763976.myjino.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); // вводим логин
        driver.findElement(By.name("password")).sendKeys("ololo_admin"); // вводим пароль
        driver.findElement(By.name("login")).click(); // жмем кнопку входа

        driver.navigate().to("http://j763976.myjino.ru/admin/?app=countries&doc=countries"); // открываем страницу списка стран

        //  получаем локаторы
        //-----------------------------------
        String locCountriesList = "//table[@class='dataTable']//td[5]"; // локатор списка стран
        String locZonesList = "//table[@class='dataTable']//td[6]"; // локатор списка зон

        // проверяем сортировку
        //--------------------------------
        List<WebElement> countriesElementsList = driver.findElements(By.xpath(locCountriesList)); // получаем список всех стран
        //List<WebElement> zonesElementsList = driver.findElements(By.xpath(locZonesList)); // получаем список всех зон
        String[] countiesNamesArr = new String[countriesElementsList.size()]; // создаем исходный массив для списка названий стран
        String[] countiesNamesArrSorted = new String[countriesElementsList.size()]; // создаем массив для списка названий стран, который будет сортироваться

        // заполняем массивы одинаковыми данными
        for (int i = 0; i < countriesElementsList.size(); i++) {
            String countryName = countriesElementsList.get(i).getText(); // получаем название текущей страны
            countiesNamesArr[i] = countryName; // добавляем название страны в исходный массив
            countiesNamesArrSorted[i] = countryName; // добавляем название страны в сортируемый массив
        }

        Arrays.sort(countiesNamesArrSorted); // делаем сортировку одного из массивов

        // проверяем, что после сортировки, порядок данных в массивах совпадает
        Assert.assertTrue(Arrays.toString(countiesNamesArr).equals(Arrays.toString(countiesNamesArrSorted)));

    }

    public void checkSorting(List<WebElement> countriesElementsList){
        
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
