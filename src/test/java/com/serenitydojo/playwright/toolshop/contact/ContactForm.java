package com.serenitydojo.playwright.toolshop.contact;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import net.serenitybdd.annotations.Step;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactForm {
    private final Page page;
    private Locator firstNameField;
    private Locator lastNameField;
    private Locator emailNameField;
    private Locator messageField;
    private Locator subjectField;
    private Locator sendButton;

    public ContactForm(Page page) {
        this.page = page;
        this.firstNameField = page.getByLabel("First name");
        this.lastNameField = page.getByLabel("Last name");
        this.emailNameField = page.getByLabel("Email");
        this.messageField = page.getByLabel("Message");
        this.subjectField = page.getByLabel("Subject");
        this.sendButton = page.getByText("Send");
    }

    @Step("Set first name to '{0}'")
    public void setFirstName(String firstName) {
        firstNameField.fill(firstName);
    }

    @Step("Set last name to '{0}'")
    public void setLastName(String lastName) {
        lastNameField.fill(lastName);
    }

    @Step("Set email to '{0}'")
    public void setEmail(String email) {
        emailNameField.fill(email);
    }

    @Step("Set message to '{0}'")
    public void setMessage(String message) {
        messageField.fill(message);
    }

    @Step("Select subject '{0}'")
    public void selectSubject(String subject) {
        subjectField.selectOption(subject);
    }

    @Step("Attach file")
    public void setAttachment(Path fileToUpload) {
        page.setInputFiles("#attachment", fileToUpload);
    }

    @Step("Submit the contact form")
    public void submitForm() {
        page.waitForTimeout(250);
        sendButton.click();
    }

    public Locator alertMessage() {
        return page.getByRole(AriaRole.ALERT);
    }

    @Step("Clear the '{0}' field")
    public void clearField(String fieldName) {
        page.getByLabel(fieldName).clear();
        page.waitForTimeout(50);
        assertThat(page.getByLabel(fieldName)).isEmpty();
    }
}
