package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright
public class ASimplePlaywrightTest {

    @Test
    void shouldShowThePageTitle(Page page) {
        // TODO: Write your first playwright test here
        //Playwright playwright=Playwright.create();                                                             // Playwright object created with create() method
        //Browser browser = playwright.chromium().launch();                                            //Browser object created for chrome
        //Page page = browser.newPage();                                                                       //Page created

        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
        System.out.println("Test passed");
        //browser.close();
        //playwright.close();

    }

    @Test
    void testToSearch(Page Page){
        Playwright playwright=Playwright.create();                                                             // Playwright object created with create() method
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));                                            //Browser object created for chrome
        Page page = browser.newPage();

        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int count_of_results = page.locator(".card").count();
        Assertions.assertTrue(count_of_results>0);
        System.out.println("The Number of Search Results:");
        System.out.println(count_of_results);

        browser.close();
        playwright.close();

    }
}
