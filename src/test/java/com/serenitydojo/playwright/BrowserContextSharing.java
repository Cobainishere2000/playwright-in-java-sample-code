package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;


public class BrowserContextSharing {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;

    Page page;

    @BeforeAll
    public static void SetUpBrowser(){

        // Before all tests this will create a browser context that will be shared by
        // all the tests we have declared everything as private and static
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();   // Created a browserContext
    }

    @BeforeEach
    public void Setup(){
        page = browserContext.newPage();    //This will run before each test

        //Its like Browser will only open once but for each test a new tab will open
    }

    @AfterAll
    public static void teardown(){
        browser.close();
        playwright.close();

        //Note Afterall is changed to @AfterAll
    }

    @Test
    void verifyTitle2(){
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
        System.out.println("Test passed");
    }

    @Test
    void SearchForPliers(){
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int count_of_results = page.locator(".card").count();
        Assertions.assertTrue(count_of_results>0);
        System.out.println("The Number of Search Results:");
        System.out.println(count_of_results);
    }

}
