package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ContactUsPageLocators {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void Setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/contact");
    }

    @AfterEach
    void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void EnterDataAndCheckForFieldErrorMessage(){


        page.locator("#first_name").fill("");
        //page.getByPlaceholder("Your First Name").click();
        //PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Saptarshi");
        //page.getByLabel("Last Name").fill("Pal");

        page.locator("#message").fill("sapchester@mailinator.com");

        page.locator(".btnSubmit").click();
        page.waitForTimeout(2000);
        List<String> errorMessages = page.locator(".alert").allTextContents();
        System.out.println(errorMessages.size());
        System.out.println(errorMessages);


    }
}
