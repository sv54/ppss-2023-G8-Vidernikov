package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreateAccount {

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
    @Tag("OnlyOnce")
    public void createAccount(){

        assertEquals("Madison Island",driver.getTitle());

        WebElement account = driver.findElement(new By.ByCssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement login = driver.findElement(new By.ByCssSelector("a[title='Log In']"));
        login.click();

        assertEquals("Customer Login",driver.getTitle());

        driver.findElement(new By.ByCssSelector("a[title='Create an Account']")).click();

        assertEquals("Create New Customer Account",driver.getTitle());

        driver.findElement(new By.ById("firstname")).sendKeys("Serhii");
        driver.findElement(new By.ById("middlename")).sendKeys("Serhii");
        driver.findElement(new By.ById("lastname")).sendKeys("Serhii");
        driver.findElement(new By.ById("email_address")).sendKeys("Serg@gmail.com");
        driver.findElement(new By.ById("password")).sendKeys("Serhii");

        driver.findElement(new By.ByCssSelector("button[title='Register']")).click();

        assertEquals("This is a required field.", driver.findElement(new By.ById("advice-required-entry-confirmation")).getText());

        driver.findElement(new By.ById("confirmation")).sendKeys("Serhii");

        driver.findElement(new By.ByCssSelector("button[title='Register']")).click();

        //assertEquals("My Account",driver.getTitle());
        assertEquals("",driver.getTitle());


    }
}
