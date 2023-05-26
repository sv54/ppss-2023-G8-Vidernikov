package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    WebDriver driver;
    WebElement email;
    WebElement pass;
    WebElement login;
    WebElement error;

    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
        email = driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/ul/li[1]/div/input"));
        pass = driver.findElement(new By.ById("pass"));
        login = driver.findElement(new By.ById("send2"));
    }

    public MyAccountPage login(String email, String password){
        this.email.sendKeys(email);
        this.pass.sendKeys(password);
        login.click();
        return new MyAccountPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void setError(){
        error = driver.findElement(new By.ByXPath("/html/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li"));
    }
}
