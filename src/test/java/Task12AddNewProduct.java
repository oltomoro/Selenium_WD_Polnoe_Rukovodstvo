import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task12AddNewProduct {
    WebDriver driver;


    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void registerNewUser() {
        driver.navigate().to("http://j763976.myjino.ru/admin");

        // Авторизация
        //------------------------------
        driver.findElement(By.name("username")).sendKeys("admin"); // вводим логин
        driver.findElement(By.name("password")).sendKeys("ololo_admin"); // вводим пароль
        driver.findElement(By.name("login")).click(); // жмем кнопку входа

        // Открываем страницу нового товара
        //------------------------------
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//a[contains(@href, 'doc=catalog')]")).click(); // открываем каталог товаров
        driver.findElement(By.xpath("//td[@id='content']//a[contains(text(), 'Add New Product')]")).click(); // Открываем страницу нового товара

        // Заполняем вкладку General
        //------------------------------
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]/input")).click(); // статус товара

        String productName = "Test Product Name" + new Date().getTime();
        driver.findElement(By.name("name[en]")).sendKeys(productName); // название товара
        driver.findElement(By.name("code")).sendKeys("12345"); // код товара
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click(); // первая категория
        driver.findElement(By.xpath("//input[@data-name='Subcategory']")).click(); // вторая категория
        new Select(driver.findElement(By.name("default_category_id"))).selectByValue("1"); // категория по-умолчанию
        driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-2']")).click(); // женский пол
        driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-1']")).click(); // мужской пол
        driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-3']")).click(); // унисекс
        driver.findElement(By.name("quantity")).sendKeys(Keys.END + "1"); // количество

        File file = new File("src\\test\\resources\\human.jpg"); // указываем относительный путь к файлу
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file.getAbsolutePath()); // загружаем файл предварительно вычислив его абсолютный путь

        driver.findElement(By.name("date_valid_from")).sendKeys("04012018"); // дата с
        driver.findElement(By.name("date_valid_to")).sendKeys("04012020"); // дата по

        // Заполняем вкладку Information
        //------------------------------
        driver.findElement(By.xpath("//*[@class='tabs']//a[text() = 'Information']")).click(); // открываем вкладку Information

        new Select(driver.findElement(By.name("manufacturer_id"))).selectByValue("1"); // производитель
        driver.findElement(By.name("keywords")).sendKeys("Test keywords"); // Keywords
        driver.findElement(By.name("short_description[en]")).sendKeys("This is short description"); // Short Description
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("This is very loooong description"); // Description
        driver.findElement(By.name("head_title[en]")).sendKeys("Test head title"); // Head Title
        driver.findElement(By.name("meta_description[en]")).sendKeys("Test meta description"); // Meta Description

        // Заполняем вкладку Prices
        //------------------------------
        driver.findElement(By.xpath("//*[@class='tabs']//a[text() = 'Prices']")).click(); // открываем вкладку Prices

        // Purchase Price
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("1");

        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue("USD"); // Purchase Price Currency Code

        driver.findElement(By.name("prices[USD]")).sendKeys("100"); // price USD
        driver.findElement(By.name("prices[EUR]")).sendKeys("200"); // price EUR

        // Price USD Gross
        WebElement priceUsdGross = driver.findElement(By.name("gross_prices[USD]"));
        priceUsdGross.clear();
        priceUsdGross.sendKeys("10");

        // Price EUR Gross
        WebElement priceEurGross = driver.findElement(By.name("gross_prices[EUR]"));
        priceEurGross.clear();
        priceEurGross.sendKeys("20");


        // Сохраняем товар
        //------------------------------
        driver.findElement(By.name("save")).click();


        // Проверяем, что товар добавился
        //------------------------------
        List<WebElement> newProduct = driver.findElements(By.xpath("//table[@class='dataTable']//a[text() = '" + productName + "']"));
        Assert.assertTrue(newProduct.size() > 0);

    }

    //public String getProductCode

    @After
    public void afterTest() {
        //driver.quit();
    }
}
