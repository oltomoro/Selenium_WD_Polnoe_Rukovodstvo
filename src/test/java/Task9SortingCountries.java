import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task9SortingCountries {
    WebDriver driver;


    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        //--------------------------------
        //String locCountiesTable = "//table[@class='dataTable']";
        String locCountriesList = "//table[@class='dataTable']//td[5]/a"; // локатор списка стран
        String locZonesList = "//table[@class='dataTable']//td[4]/a"; // локатор списка зон
        String locRow = "//table[@class='dataTable']//tr[@class='row']";

        // проверяем сортировку
        //--------------------------------
        //WebElement tableParent = driver.findElement(By.xpath(locCountiesTable));
        //List<WebElement> rowsParent = driver.findElements(By.xpath("//table[@class='dataTable']//td[5]/a"));

        List<WebElement> countriesList = driver.findElements(By.xpath(locCountriesList)); // получаем список всех стран

        checkSorting(countriesList); // проверяем сортировку стран

        for (int i = 0; i < countriesList.size(); i++) {
            WebElement row = driver.findElements(By.xpath(locRow)).get(i);
            String zoneValue = row.findElement(By.xpath(".//td[6]")).getText();

            // если у страны есть зоны
            if (!zoneValue.equals("0")){
                row.findElement(By.xpath(".//td[5]/a")).click(); // переходим к списку зон
                List<WebElement> zonesList = driver.findElements(By.xpath(locZonesList)); // получаем список зон
                checkSorting(zonesList); // проверяем сортировку зон
                driver.navigate().back(); // возвращаемся к списку стран
            }
        }

    }

    // функция проверки сортировки
    public void checkSorting(List<WebElement> list){
        String[] itemsNamesArr = new String[list.size()]; // создаем исходный массив для списка названий стран/зон
        String[] itemssNamesArrSorted = new String[list.size()]; // создаем массив для списка названий стран/зон, который будет сортироваться

        // заполняем массивы одинаковыми данными
        for (int i = 0; i < list.size(); i++) {
            String itemName = list.get(i).getText(); // получаем название текущей страны/зоны
            itemsNamesArr[i] = itemName; // добавляем название страны/зоны в исходный массив
            itemssNamesArrSorted[i] = itemName; // добавляем название страны/зоны в сортируемый массив
        }

        Arrays.sort(itemssNamesArrSorted); // делаем сортировку одного из массивов

        // проверяем, что после сортировки, порядок данных в массивах совпадает
        Assert.assertTrue(Arrays.toString(itemsNamesArr).equals(Arrays.toString(itemssNamesArrSorted)));
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
