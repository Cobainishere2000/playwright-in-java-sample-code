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


public class PracticeLocators {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void Setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com");
    }

    @AfterEach
    void teardown(){
        browser.close();
        playwright.close();
    }

    @Test
    void ByTextdemo1(){

        //Get element by text
        //It will find the text even if its a link , div or whatever
        //page.getByText("Bolt Cutters").click();

        //We can also assert like
        //Assertions.assertTrue(page.getByText("Bolt Cutters").isVisible());

        //We can find by Alt text - mainly used for images
        //page.getByAltText("Combination Pliers").click();

        //Get by title to
        //page.getByTitle("ABCDEFGH").click();
        page.getByText("Bolt Cutters").click();
        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void ByAltTestdemo2(){
        page.getByAltText("Combination Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
    }

    @Test
    void Bytitledemo3(){
        page.getByText("Bolt Cutters").click();
        page.getByTitle("Practice Software Testing - Toolshop").click();
    }



    /*
    @Test
    void demo2(){
        page.navigate("https://www.account1.jnjwithme.com");

        String button_name = page.locator("button:has-text('Guide Me and Customize My Experience')").textContent();
        System.out.println(button_name);
        page.locator("button:has-text('Accept')").click();
        page.locator("button:has-text('Guide Me and Customize My Experience')").click();
        page.waitForURL("https://www.account1.jnjwithme.com/user/register?flow=guided");

        String title2 = page.title();
        System.out.println(title2);
        Assertions.assertEquals("Register", title2);


    }

     */


}
