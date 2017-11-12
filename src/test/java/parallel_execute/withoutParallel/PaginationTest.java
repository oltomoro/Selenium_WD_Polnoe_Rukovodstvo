package parallel_execute.withoutParallel;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class PaginationTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://paginationjs.com/");
        WebElement demo1 = driver.findElement(By.cssSelector("#demo1"));
        List<WebElement> items = demo1.findElements(By.cssSelector(".data-container ul li"));
        List<WebElement> pagination = demo1.findElements(By.cssSelector(".paginationjs-pages ul li"));

        pagination.get(2).click();
        wait.until(not(visibilityOf(items.get(0))));
        items = demo1.findElements(By.cssSelector(".data-container ul li"));
        Assert.assertTrue(items.get(0).getText().equals("11"));
    }

}
