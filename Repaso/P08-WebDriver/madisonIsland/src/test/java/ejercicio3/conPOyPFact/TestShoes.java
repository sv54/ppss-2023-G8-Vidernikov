package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShoes {
    WebDriver driver;
    MyAccountPage myAccountPage;
    @BeforeEach
    public void setup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        chromeOptions.setHeadless(headless);
        driver = new ChromeDriver();
        Cookies.storeCookiesToFile("Serhii@gmail.com", "Serhii");
        Cookies.loadCookiesFromFile(driver);
        myAccountPage = PageFactory.initElements(driver,MyAccountPage.class);
    }

    @AfterEach
    public void endup(){
        driver.close();
    }

    @Test
    public void compareShoes(){
        assertEquals("My Account", myAccountPage.getTitle());

        ShoesPage shoesPage = myAccountPage.toShoes();
        assertEquals("Shoes - Accessories", shoesPage.getTitle());

        Products products = shoesPage.compare();
        assertEquals("Products Comparison List - Magento Commerce", products.getTitle());
        shoesPage = products.close();

        assertEquals("Shoes - Accessories", shoesPage.getTitle());
        shoesPage.clearAll();
        assertEquals("Are you sure you would like to remove all products from your comparison?",shoesPage.message);

        assertEquals("The comparison list was cleared.",shoesPage.borrado.getText());

    }

}
