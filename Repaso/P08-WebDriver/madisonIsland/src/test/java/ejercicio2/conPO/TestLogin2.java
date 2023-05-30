package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin2 {


    WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void end(){
        driver.close();
    }

    @Test
    public void test_Login_Correct(){
        Assertions.assertEquals("Madison Island", homePage.getTitle());
        CustomerLoginPage loginPage = homePage.enterLoginPage();
        Assertions.assertEquals("Customer Login", loginPage.getTitle());
        MyAccountPage accountPage = loginPage.login("Serg@gmail.com", "Serhii");
        Assertions.assertEquals("My Account", accountPage.getTitle());
    }

    @Test
    public void test_Login_Incorrect(){
        Assertions.assertEquals("Madison Island", homePage.getTitle());
        CustomerLoginPage loginPage = homePage.enterLoginPage();
        Assertions.assertEquals("Customer Login", loginPage.getTitle());
        MyAccountPage accountPage = loginPage.login("Serg@gmail.com", "Serhiiiiiiiiiiiii");
        assertEquals("Invalid login or password.",loginPage.advice.getText());

    }

}
