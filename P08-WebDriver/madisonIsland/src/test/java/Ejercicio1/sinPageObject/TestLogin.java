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
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");

    }


    @AfterEach
    public void endup(){
        driver.close();
    }

    @Test
    public void loginOK(){
        String titulo = driver.getTitle();
        assertEquals("Madison Island", titulo);
        WebElement Account = driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[2]/div/a"));
        Account.click();
        driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[5]/div/ul/li[6]/a")).click();

        titulo = driver.getTitle();
        assertEquals("Customer Login", titulo);

        driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/ul/li[1]/div/input")).sendKeys("Serhii@gmail.com");
        driver.findElement(new By.ById("send2")).click();

        String advice = driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/ul/li[2]/div/div")).getText();
        assertEquals("This is a required field.", advice);

        driver.findElement(new By.ById("pass")).sendKeys("Serhii");
        driver.findElement(new By.ById("send2")).click();

        titulo = driver.getTitle();
        assertEquals("My Account", titulo);
    }

    @Test
    public void loginFailed(){
        String titulo = driver.getTitle();
        assertEquals("Madison Island", titulo);
        WebElement Account = driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[2]/div/a"));
        Account.click();
        driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[5]/div/ul/li[6]/a")).click();

        titulo = driver.getTitle();
        assertEquals("Customer Login", titulo);

        driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/ul/li[1]/div/input")).sendKeys("Serhii@gmail.com");
        driver.findElement(new By.ById("pass")).sendKeys("Incorrecto");
        driver.findElement(new By.ById("send2")).click();

        String invalido = driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li")).getText();
        assertEquals("Invalid login or password.", invalido);


    }


}
