package tests;

import org.junit.jupiter.api.Test;
import pages.FormPage;
import io.qameta.allure.Description;



public class FormTest {

    @Test
    @Description("Test form submission and check the success message")
    public void testFormSubmission() {
        FormPage formPage = new FormPage();
        formPage.openPage();
        formPage.fillForm();
        formPage.submitForm();
        formPage.verifySuccessMessage();
    }
}
