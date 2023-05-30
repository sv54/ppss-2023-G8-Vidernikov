package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin {

    WebDriver driver;
    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @AfterEach
    public void close(){
        driver.close();
    }
    @Test
    public void loginOK(){
        assertEquals("Madison Island",driver.getTitle());

        WebElement account = driver.findElement(new By.ByCssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement login = driver.findElement(new By.ByCssSelector("a[title='Log In']"));
        login.click();

        assertEquals("Customer Login",driver.getTitle());

        driver.findElement(new By.ById("email")).sendKeys("Serg@gmail.com");
        driver.findElement(new By.ById("send2")).click();
        assertEquals("This is a required field.",driver.findElement(new By.ById("advice-required-entry-pass")).getText());

        driver.findElement(new By.ById("pass")).sendKeys("Serhii");
        driver.findElement(new By.ById("send2")).click();

        assertEquals("My Account",driver.getTitle());
    }

    @Test
    public void loginFailed(){
        assertEquals("Madison Island",driver.getTitle());

        WebElement account = driver.findElement(new By.ByCssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement login = driver.findElement(new By.ByCssSelector("a[title='Log In']"));
        login.click();

        assertEquals("Customer Login",driver.getTitle());

        driver.findElement(new By.ById("email")).sendKeys("Serg@gmail.com");
        driver.findElement(new By.ById("send2")).click();

        driver.findElement(new By.ById("pass")).sendKeys("Serhiiiiii");
        driver.findElement(new By.ById("send2")).click();

        assertEquals("Invalid login or password.",driver.findElement(new By.ByXPath("//*[@id=\"top\"]/body/div[1]/div[2]/div[2]/div/div/div[2]/ul/li/ul/li")).getText());
    }
}
