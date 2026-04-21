package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
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
    void EnterData(){


        page.locator("#first_name").fill("Ayan");
        page.getByPlaceholder("Your First Name").fill("Saptarshi");
        PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Saptarshi");
        page.getByLabel("Last Name").fill("Pal");

        page.getByLabel("Email address").fill("sapchester@mailinator.com");

        page.locator(".btnSubmit").click();

        List<String> errorMessages = page.locator(".alert").allTextContents();
        System.out.println(errorMessages);


    }
}
