package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class aSimplePlaywrightRefactored {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void Setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    void teardown(){
        browser.close();
        playwright.close();
    }

    @Test
    void verifyTitleOfPage(){
        page.navigate("https://www.account1.jnjwithme.com");

        String title = page.title();
        System.out.println(title);
        Assertions.assertEquals("J&J withMe", title);
    }



}
