package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import static org.assertj.core.api.Assertions.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class PlaywrightWaits {


    @BeforeEach
    void OpenHomePage(Page page){
        page.navigate("https://practicesoftwaretesting.com");

        //Now we will add waits
        page.waitForSelector("[data-test=product-name]");
    }


    @Test
    void VerifyProductNames(Page page){

        List<String> product_names = page.getByTestId("product-name").allTextContents();
        System.out.println(product_names);
        assertThat(product_names).contains(" Pliers ", " Bolt Cutters ", " Hammer ");
    }

    @Test
    void VerifyProductImages(Page page){
        List<String> imagetitles = page.locator(".card-img-top").all()
                .stream()
                .map(img -> img.getAttribute("alt"))
                .toList();

        assertThat(imagetitles).contains("Pliers", "Bolt Cutters", "Hammer");
    }

}
