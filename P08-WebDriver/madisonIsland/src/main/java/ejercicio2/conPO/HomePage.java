package ejercicio2.conPO;

import ejercicio2.conPO.CustomerLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    WebDriver driver;
    WebElement account;
    WebElement loginLink;

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/");
        account = driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[2]/div/a"));
    }

    public CustomerLoginPage loginPage(){
        account.click();
        loginLink = driver.findElement(new By.ByXPath("/html/body/div/div[2]/header/div/div[5]/div/ul/li[6]/a"));
        loginLink.click();
        return new CustomerLoginPage(driver);
    }

    public String getTitle(){
       return driver.getTitle();
    }
}
