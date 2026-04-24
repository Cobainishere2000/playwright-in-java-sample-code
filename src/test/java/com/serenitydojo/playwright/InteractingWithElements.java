package com.serenitydojo.playwright;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class InteractingWithElements {

    @Test
    void FillForm(Page page) throws URISyntaxException {

        page.navigate("https://practicesoftwaretesting.com/contact");

        var firstname = page.locator("#first_name");
        var lastname = page.getByLabel("Last name");
        var email = page.getByPlaceholder("Your email");
        //message is a multiline field - text area
        var message = page.getByLabel("Message");
        var subject_field = page.getByLabel("Subject");
        var upload_field = page.getByLabel("Attachment");

        firstname.fill("Saptarshi");
        lastname.fill("Pal");
        email.fill("sapchester85@mailinator.com");
        message.fill("Hello World ! This is Saptarshi");


        //Interacting with Dropdowns - there are a few ways
        //Dropdown is a select element with elements inside option tags
        subject_field.selectOption("Warranty");  //--- this is simplest by what text we see
        //Now lets see the SelectOption() object
        //subject_field.selectOption(new SelectOption().setIndex(2));
        //Or
        //subject_field.selectOption(new SelectOption().setValue("status-of-order"));
        //or
        //subject_field.selectOption(new SelectOption().setLabel("Status of my order"))


        //Uploading Files
        Path file_path = Paths.get(ClassLoader.getSystemResource("data/Upload1.txt").toURI());
        page.setInputFiles("#attachment", file_path);
        page.waitForTimeout(5000);


        PlaywrightAssertions.assertThat(firstname).hasValue("Saptarshi");
        PlaywrightAssertions.assertThat(lastname).hasValue("Pal");
        PlaywrightAssertions.assertThat(email).hasValue("sapchester85@mailinator.com");
        PlaywrightAssertions.assertThat(message).hasValue("Hello World ! This is Saptarshi");
        PlaywrightAssertions.assertThat(subject_field).hasValue("warranty");

        //Test will fail if we use "Warranty" as the value because in html the option is "warranty"
        //But the Dropdown text has "Warranty"
    }

    @ParameterizedTest
    @ValueSource(strings = {"First name", "Last name", "Email", "Message"})
    void mandatoryFields(String fieldname, Page page){     //Note Page should come at the end in list of parameters
        page.navigate("https://practicesoftwaretesting.com/contact");

        var firstname = page.locator("#first_name");
        var lastname = page.getByLabel("Last name");
        var email = page.getByPlaceholder("Your email");
        //message is a multiline field - text area
        var message = page.getByLabel("Message");
        var send_button = page.getByText("Send");
        //Fill in All the data
        firstname.fill("Saptarshi");
        lastname.fill("Pal");
        email.fill("sapchester85@mailinator.com");
        message.fill("Hello World ! This is Saptarshi");

        //Clear out one
        page.getByLabel(fieldname).clear();
        send_button.click();

        //Assert Error message for each mandatory field is displayed
        var error_msg = page.getByRole(AriaRole.ALERT).getByText(fieldname+" is required");
        PlaywrightAssertions.assertThat(error_msg).isVisible();

    }

}
