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
    void Setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com");
    }

    @AfterEach
    void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void ByTextdemo1() {

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
    void ByAltTestdemo2() {
        page.getByAltText("Combination Pliers").click();
        PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
    }

    @Test
    void Bytitledemo3() {
        page.getByText("Bolt Cutters").click();
        page.getByTitle("Practice Software Testing - Toolshop").click();

        playwright.selectors().setTestIdAttribute("data-testid");

        page.getByTestId("Data_First_Name").fill("Saptarshi");
    }
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

        USING LABELS and PLACEHOLDERS
        Locating using Labels and Placeholder texts present for input fields

        Suppose there is a form with Fields like First name, Last name and Email
        These will be input fields and there will be some labels for the fields with
        Some Label text as well - a label is associated with a input field using some id

        <label for="firstname" class="form-label">First Name</label>

        Here firstname is the id for the First Name input field
        Also the Input fields will have some placeholder text

        We can find the elements using both the Label text and the placeholder text


        page.getByLabel("First Name").fill("Saptarshi");
        page.getByLabel("Last Name").fill("Pal");

        page.getByPlaceholder("Your First Name").fill();
        this will match with the placeholder text partially - like contains

        ****Use getByLabel() for Input fields and checkboxes
        ****Cannot getByPlaceholder() for checkboxes and dropdowns
     */

