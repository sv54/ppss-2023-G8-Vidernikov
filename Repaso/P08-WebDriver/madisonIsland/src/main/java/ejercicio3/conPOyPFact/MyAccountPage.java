package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage {
    WebDriver driver;
    @FindBy (xpath = "//*[@id=\"nav\"]/ol/li[3]/a") WebElement Accessories;
    @FindBy (xpath = "//*[@id=\"nav\"]/ol/li[3]/ul/li[4]/a") WebElement shoesLink;

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
    }

    public ShoesPage toShoes(){
        Actions builder = new Actions(driver);
        builder.moveToElement(Accessories);
        builder.perform();
        shoesLink.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
