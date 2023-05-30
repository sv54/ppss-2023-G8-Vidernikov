package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    WebElement account, login;

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/");
        account = driver.findElement(new By.ByCssSelector("#header > div > div.skip-links > div > a"));
        login = driver.findElement(new By.ByCssSelector("a[title='Log In']"));
    }

    public CustomerLoginPage enterLoginPage(){
        account.click();
        login.click();
        return new CustomerLoginPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }

}
