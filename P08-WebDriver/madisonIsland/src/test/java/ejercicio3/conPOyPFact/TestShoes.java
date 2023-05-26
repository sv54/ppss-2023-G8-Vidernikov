package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.AfterEach;
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
        //Cookies.storeCookiesToFile("Serhii@gmail.com", "Serhii");
        Cookies.loadCookiesFromFile(driver);
        myAccountPage = PageFactory.initElements(driver,MyAccountPage.class);
    }

    @AfterEach
    public void endup(){
        driver.close();
    }

    @Test
    public void compareShoes(){
        Cookies.printCookies(driver,"");
        assertEquals("My Account", myAccountPage.getTitulo());

        ShoesPage shoesPage = myAccountPage.goToShoes();
        assertEquals("Shoes - Accessories", shoesPage.getTitle());

        ProductComparisonPage productComparisonPage = shoesPage.addToCompare();
        assertEquals("Products Comparison List - Magento Commerce", productComparisonPage.getTitle());
        shoesPage = productComparisonPage.close();

        assertEquals("Shoes - Accessories", shoesPage.getTitle());
        shoesPage.clearAll();
        assertEquals("The comparison list was cleared.", shoesPage.comparacionBorrada.getText());


    }

}
