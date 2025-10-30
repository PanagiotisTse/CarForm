package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String selectRandomOption(By locator) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        int startIndex = 1; //Ignore Select year option
        Random rand = new Random();
        int index = startIndex + rand.nextInt(options.size() - startIndex);

        select.selectByIndex(index);
        //Just for confirmation which year value is selected
        System.out.println("Selected option: " + select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText();
    }
}
