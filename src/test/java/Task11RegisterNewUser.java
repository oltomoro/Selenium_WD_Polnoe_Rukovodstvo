import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task11RegisterNewUser {
    WebDriver driver;


    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void registerNewUser(){
        // Регистрация
        //------------------------------
        String randEmail = generateEmail(20); // генерируем уникальный email
        String password = "Zaq1xsw2"; // Пароль

        driver.navigate().to("http://j763976.myjino.ru/en/create_account");

        driver.findElement(By.xpath("//*[@name='tax_id']")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@name='company']")).sendKeys("Noname");
        driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys("John");
        driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys("Doe");
        driver.findElement(By.xpath("//*[@name='address1']")).sendKeys("test address 1");
        driver.findElement(By.xpath("//*[@name='address2']")).sendKeys("test address 2");
        driver.findElement(By.xpath("//*[@name='postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@name='city']")).sendKeys("New York");

        // выбираем страну
        driver.findElement(By.xpath("//*[@class='select2-selection__rendered']")).click();
        driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys("United States");
        driver.findElement(By.xpath("//li[text()='United States']")).click();

        // выбираем штат
//        Select state = new Select(driver.findElement(By.xpath("//select[@name='zone_code']")));
//        state.selectByValue("FL");
        new Select(driver.findElement(By.xpath("//select[@name='zone_code']"))).selectByValue("FL");

        driver.findElement(By.xpath("//*[@name='email']")).sendKeys(randEmail);
        driver.findElement(By.xpath("//*[@name='phone']")).sendKeys("+11111111111");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@name='confirmed_password']")).sendKeys(password);

        driver.findElement(By.xpath("//*[@name='create_account']")).click(); // жмем кнопку регистрации

        // Выход из системы
        //------------------------------
        driver.findElement(By.xpath("//*[@id='navigation']//a[contains(@href, 'logout')]")).click();

        // Авторизация
        //------------------------------
        driver.findElement(By.xpath("//*[@id='box-account-login']//*[@name='email']")).sendKeys(randEmail);
        driver.findElement(By.xpath("//*[@id='box-account-login']//*[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='box-account-login']//*[@name='login']")).click();

        // Выход из системы
        //------------------------------
        driver.findElement(By.xpath("//*[@id='navigation']//a[contains(@href, 'logout')]")).click();
    }

    public String generateEmail(int randStringLength){
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randString = new StringBuilder();
        randString.append("test_user_");
        int count = (int) (Math.random() * randStringLength);
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));

        randString.append("@p33.org");

        return randString.toString();
    }



    @After
    public void afterTest(){
        driver.quit();
    }
}
