package com.serenitydojo.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class WaitsInNestedClass {

    @Nested
    class AutomaticWaits{

        @BeforeEach
        void openHomePage(Page page){
            page.navigate("https://practicesoftwaretesting.com");
        }

        @Test
        void shouldwaitforCheckboxFilter(Page page){

            var chkbx = page.getByLabel("Screwdriver");
            chkbx.click();
            //Implicitly Waits for element to be clickable
            PlaywrightAssertions.assertThat(chkbx).isChecked();
        }

        @Test
        void ShouldFilterTestByCategory(Page page){

            page.getByRole(AriaRole.MENUBAR).getByText("Categories").click();
            page.getByRole(AriaRole.MENUBAR).getByText("Power Tools").click();

            //Without this Wait the test will fail-
            page.waitForSelector(".card");

            var fileredProducts = page.getByTestId("product-name").allInnerTexts();

            assertThat(fileredProducts).contains("Belt Sander", "Sheet Sander", "Random Orbit Sander");

        }


        @Test
        void MessageAppearsAndThenDisappears(Page page){

            page.getByText("Bolt Cutters").click();
            page.getByText("Add to cart").click();

            //wait for toaster message
            PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
            PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).hasText("Product added to shopping cart.");

            //check for disappearance
            page.waitForCondition( () -> page.getByRole(AriaRole.ALERT).isHidden());

        }

    }
}
