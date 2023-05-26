package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin2 {
    WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void endup(){
        driver.close();
    }
    @Test
    public void test_Login_Correct(){
        assertEquals("Madison Island", homePage.getTitle());
        CustomerLoginPage loginPage =  homePage.loginPage();
        assertEquals("Customer Login", loginPage.getTitle());
        MyAccountPage accountPage = loginPage.login("Serhii@gmail.com", "Serhii");
        assertEquals("My Account", accountPage.getTitle());

    }
    @Test
    public void test_Login_Incorrect(){
        assertEquals("Madison Island", homePage.getTitle());
        CustomerLoginPage loginPage =  homePage.loginPage();
        assertEquals("Customer Login", loginPage.getTitle());
        MyAccountPage accountPage = loginPage.login("Serhii@gmail.com", "1111123Serhii");
        loginPage.setError();
        assertEquals("Invalid login or password.", loginPage.error.getText());
    }
}
