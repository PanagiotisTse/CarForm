package TCs;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseTest;
import pages.carFormPage;

import java.util.List;

public class carForm extends BaseTest {
    private carFormPage form;

    @BeforeMethod
    public void setUpPage() {
        driver.get("file:///C:/Users/pants/Downloads/QA%20Programming%20Exercise.html"); //path to HTML file
        form = new carFormPage(driver);
        form.checkSiteIsLoaded();
    }

    @Test (priority = 1, description = "User enters valid values in the form")
    public void enterValidValues() {
        form.enterCarRegistration("ABC1234");
        String selectedYear = form.selectYear();
        form.clickSubmitButton();
        String expected = "Success! Selected ABC1234 with year " + selectedYear;
        Assert.assertEquals(form.getSuccessMessage(), expected);
    }

    @Test (priority = 2, description = "User enters empty values in the form")
    public void enterEmptyValues() {
        form.enterCarRegistration("");
        form.clickSubmitButton();
        Assert.assertEquals(form.getErrorMessage(), "There was an error!");
    }

    @Test (priority = 3, description = "User enters empty registration number in the form")
    public void enterEmptyRegistrationNumber() {
        form.enterCarRegistration("");
        String selectedYear = form.selectYear();
        form.clickSubmitButton();
        Assert.assertEquals(form.getErrorMessage(), "There was an error!");
    }

    @Test (priority = 4, description = "User enters empty year in the form")
    public void enterEmptyYear() {
        form.enterCarRegistration("ABC1234");
        form.clickSubmitButton();
        Assert.assertEquals(form.getErrorMessage(), "There was an error!");
    }

    @Test (priority = 5, description = "User enters invalid registration number in the form")
    public void enterInvalidRegistrationNumber() throws InterruptedException {
        List<String> invalidRegs = List.of("ABC", "!!@@##", "123", "ada7yf765678");
        form.testInvalidRegistrations(invalidRegs);
    }

    @Test (priority = 6, description = "User enters valid registration number and submit form multiple times with all available year options")
    public void submitFormMultipleTimesPerSameRegistrationNumber() throws InterruptedException {
        form.enterCarRegistration("ABC1234");
        form.submitAllDropdownOptions();
    }
}
