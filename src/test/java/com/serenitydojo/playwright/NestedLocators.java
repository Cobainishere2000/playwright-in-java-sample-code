package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class NestedLocators {
    /*
    We dont need these setup and teardown here
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void Setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        playwright.selectors().setTestIdAttribute("data-test");
    }

    @AfterEach
    void teardown() {
        browser.close();
        playwright.close();
    }

     */

    @Test
    void searchPliers(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByPlaceholder("Search").fill("Pliers");
        page.waitForTimeout(5000);
        //page.locator("button[type='submit']").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        page.waitForTimeout(5000);

        PlaywrightAssertions.assertThat(page.locator(".card")).hasCount(5);
        List<String> my_list = page.locator(".card").allTextContents();
        System.out.println(my_list);

        //List<String> product_name = page.getByTestId("product-name").allTextContents();
        //System.out.println(product_name);
        /*Getting Price Of Items

        List<Float> price = new ArrayList<>();
        for(int i = 0; i < my_list.size(); i++) {
            String k = my_list.get(i);
            int index = k.indexOf('$');
            String pr = k.substring(index + 1);
            price.add(Float.valueOf(pr));
        }
        System.out.println(price);

         */

        Locator out_of_Stock = page.locator(".card")
                .filter(new Locator.FilterOptions().setHasText("Out of stock"))
                .getByTestId("product-name");

        PlaywrightAssertions.assertThat(out_of_Stock).hasText("Long Nose Pliers");



    }




}
