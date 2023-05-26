package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver driver;
    @FindBy (xpath="/html/body/div/div[2]/header/div/div[3]/nav/ol/li[3]/a") WebElement accesorios;
    @FindBy (xpath = "/html/body/div/div[2]/header/div/div[3]/nav/ol/li[3]/ul/li[4]/a") WebElement shoesLink;


    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
    }

    public ShoesPage goToShoes(){
        Actions builder = new Actions(driver);
        builder.moveToElement(accesorios);
        builder.perform();
        shoesLink.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getTitulo(){
        return driver.getTitle();
    }
}
