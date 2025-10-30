package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class carFormPage extends BasePage {

    private final By form = By.id("hd-form");
    private final By carRegNumber = By.id("input-number-plates");
    private final By yearDropdown = By.id("select-year");
    private final By submitBtn = By.id("btn-submit");
    private final By successMessage = By.cssSelector(".alert-success");
    private final By errorMessage = By.cssSelector(".alert-danger");

    public carFormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkSiteIsLoaded() {
        Assert.assertTrue(driver.findElement(form).isDisplayed());
        Assert.assertTrue(driver.findElement(carRegNumber).isDisplayed());
    }

    public void enterCarRegistration(String carNumber) {
        driver.findElement(carRegNumber).clear();
        driver.findElement(carRegNumber).sendKeys(carNumber);
    }

    public String selectYear() {
        return selectRandomOption(yearDropdown);
    }

    public void clickSubmitButton() {
        driver.findElement(submitBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void testInvalidRegistrations(List<String> badRegs) throws InterruptedException {
        for (String reg : badRegs) {
            System.out.println("Testing registration: " + reg);
            new carFormPage(driver).enterCarRegistration(reg);
            new carFormPage(driver).selectYear();
            new carFormPage(driver).clickSubmitButton();
            Thread.sleep(2000);
            new carFormPage(driver).getErrorMessage();
        }
    }

    public void submitAllDropdownOptions() throws InterruptedException {
        WebElement dropdownElement = driver.findElement(yearDropdown);
        Select select = new Select(dropdownElement);

        List<WebElement> options = select.getOptions();

        for (int i = 1; i < options.size(); i++) {
            select.selectByIndex(i);
            String selectedValue = select.getFirstSelectedOption().getText();
            System.out.println("Selected value: " + selectedValue);
            driver.findElement(submitBtn).click();
            Thread.sleep(500);

            WebElement message = driver.findElement(By.cssSelector(".alert-success"));
            System.out.println("Message: " + message.getText());
        }
    }
}
