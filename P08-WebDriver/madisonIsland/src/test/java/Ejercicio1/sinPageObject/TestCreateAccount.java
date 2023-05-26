package Ejercicio1.sinPageObject;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateAccount {

    WebDriver driver;


    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
        driver = new ChromeDriver();

    }


    @AfterEach
    public void endup(){
        driver.close();
    }

    @Test
    @Tag("OnlyOnce")
    public void createAccount(){

        driver.get("http://demo-store.seleniumacademy.com/");
        String titulo = driver.getTitle();

        assertEquals("Madison Island", titulo);

        WebElement Account = driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[2]/div/a"));
        Account.click();
        driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[5]/div/ul/li[6]/a")).click();

        titulo = driver.getTitle();
        assertEquals("Customer Login", titulo);

        driver.findElement(new By.ByCssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a")).click();
        driver.findElement(new By.ById("firstname")).sendKeys("Serhii");
        driver.findElement(new By.ById("middlename")).sendKeys("Serhii");
        driver.findElement(new By.ById("lastname")).sendKeys("Serhii");
        driver.findElement(new By.ById("email_address")).sendKeys("Serhii5@gmail.com");
        driver.findElement(new By.ById("password")).sendKeys("Serhii");

        //Enviamos
        driver.findElement(new By.ByCssSelector("#form-validate > div.buttons-set > button")).click();

        String advice = driver.findElement(new By.ById("advice-required-entry-confirmation")).getText();
        assertEquals("This is a required field.", advice);
        //Rellenamos
        driver.findElement(new By.ById("confirmation")).sendKeys("Serhii");

        //Enviamos otra vez
        driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/form")).submit();

        titulo = driver.getTitle();
        assertEquals("My Account", titulo);

    }

}
