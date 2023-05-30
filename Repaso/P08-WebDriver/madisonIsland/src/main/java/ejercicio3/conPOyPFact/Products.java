package ejercicio3.conPOyPFact;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products {
    WebDriver driver;
    String myHandleId;
    String myHandleIdFrom;


    public Products(WebDriver driver, String from){
        this.driver= driver;
        myHandleId = driver.getWindowHandle();
        myHandleIdFrom = from;
    }

    public ShoesPage close(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(new By.ByXPath("/html/body/div/div[3]/button")));
        driver.findElement(new By.ByXPath("/html/body/div/div[3]/button")).click();
        driver.switchTo().window(myHandleIdFrom);
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getTitle(){
        return driver.getTitle();
    }

}
