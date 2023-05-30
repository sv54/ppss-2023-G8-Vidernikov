package ejercicio3.conPOyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class ShoesPage {
    WebDriver driver;

    @FindBy (xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]/div/div[2]/ul/li[2]/a") WebElement wingtipShoe;
    @FindBy (xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]/div/div[2]/ul/li[2]/a") WebElement suedeShoe;

    @FindBy (xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[4]/div/div[2]/div/button") WebElement compare;

    @FindBy (xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[4]/div/div[2]/div/a") WebElement clearAll;
    @FindBy (xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[2]/ul/li/ul/li") WebElement borrado;

    String message;
    String myHandleId;
    public ShoesPage(WebDriver driver){
        this.driver = driver;
        myHandleId = driver.getWindowHandle();
    }
    public void selectShoeToCompare(int number) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (number) {
            case 5:
                jse.executeScript("arguments[0].scrollIntoView();", wingtipShoe);
                wingtipShoe.click();
                break;
            case 6:
                jse.executeScript("arguments[0].scrollIntoView();", suedeShoe);
                suedeShoe.click();
                break;
        }
    }

    public Products compare(){
        selectShoeToCompare(5);
        selectShoeToCompare(6);
        compare.click();
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        System.out.println("ID 0: "+handleIds[0]); //manejador de la ventana ShoesPage
        System.out.println("ID 1: "+handleIds[1]); //manejador de la venana ProductComparisonPage
        driver.switchTo().window(handleIds[1]);
        return new Products(driver, handleIds[0]);
    }

    public String clearAll(){
        clearAll.click();
        Alert alert = driver.switchTo().alert();
        message = alert.getText();
        alert.accept();
        return message;
    }
    public String getTitle(){
        return driver.getTitle();
    }

}
