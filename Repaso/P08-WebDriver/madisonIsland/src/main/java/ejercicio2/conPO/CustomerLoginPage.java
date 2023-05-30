package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    WebDriver driver;
    WebElement email, password, login, advice;

    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
        email = driver.findElement(new By.ById("email"));
        password = driver.findElement(new By.ById("pass"));
        login = driver.findElement(new By.ById("send2"));
    }

    public MyAccountPage login(String email, String pass){
        this.email.sendKeys(email);
        password.sendKeys(pass);
        login.click();
        try {
            advice = driver.findElement(new By.ByXPath("//*[@id=\"top\"]/body/div[1]/div[2]/div[2]/div/div/div[2]/ul/li/ul/li"));

        }catch (Exception e){

        }
        return new MyAccountPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }



}
