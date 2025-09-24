package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class FormPage {
    private final String URL = "https://practice-automation.com/form-fields/";

    private SelenideElement nameInput = $("#name-input"); // CSS-селектор по ID

    private SelenideElement passwordInput = $x("//label[text()='Password ']/input"); // XPath-селектор

    private SelenideElement drinkMilk = $("#drink2");// CSS-селектор по ID
    private SelenideElement drinkCoffee = $("#drink3");// CSS-селектор по ID

    private SelenideElement colorYellow = $("#color3");// CSS-селектор по ID

    private SelenideElement automationDropdown = $("#automation");// CSS-селектор по ID

    private SelenideElement emailInput = $("#email");// CSS-селектор по ID
    private SelenideElement messageInput = $("#message");// CSS-селектор по ID
    private SelenideElement submitButton = $("#submit-btn");// CSS-селектор по ID

    public void openPage() {
        open(URL);
    }

    public void fillForm() {
        nameInput.setValue("Test User");

        passwordInput.shouldBe(visible).setValue("Password123");

        drinkMilk.scrollIntoView(true).shouldBe(visible).click();
        drinkCoffee.scrollIntoView(true).shouldBe(visible).click();

        actions().moveToElement(colorYellow).click().perform();

        automationDropdown.scrollIntoView(true).selectOptionByValue("yes");

        emailInput.scrollIntoView(true).setValue("test@example.com");
        String toolsCount = "5"; 
        messageInput.scrollIntoView(true).setValue(toolsCount);

        String longestTool = "Katalon Studio";
        messageInput.append(longestTool);
    }

    public void submitForm() {
        submitButton.scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(5));
        actions().moveToElement(submitButton).perform();
        try {
            submitButton.click();
        } catch (Exception e) {
            System.out.println("Обычный клик не сработал, пробуем JavaScript-клик");
            executeJavaScript("arguments[0].click();", submitButton);
        }
    }
    public void verifySuccessMessage() {
        try {
            Alert alert = switchTo().alert();
            String alertText = alert.getText();
            
            if (alertText.equals("Message received!")) {
                alert.accept(); 
            } else {
                System.out.println("Unexpected alert text: " + alertText);
            }
        } catch (NoAlertPresentException e) {
            System.out.println("Alert not present.");
        }
    }
    
}
