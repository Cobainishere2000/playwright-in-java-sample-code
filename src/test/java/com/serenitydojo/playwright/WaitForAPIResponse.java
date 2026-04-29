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

import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class WaitForAPIResponse {

    //We are sorting some search results. It will call an API to find results sorted by Price
    //We will be waiting for the sort API response to get the sorted prices
    @Test
    void SortAPIResponseCheck(Page page){
        page.navigate("https://practicesoftwaretesting.com");

        //Sort by Price
        page.waitForResponse("https://api.practicesoftwaretesting.com/products?page=0&sort=price,desc&between=price,1,100&is_rental=false",
                () -> {
                        page.getByTestId("sort").selectOption("Price (High - Low)");
                      });  //First one is the API we are checking, and second is the lambda expression
                    //which describes the action that triggers the API

        //page.getByTestId("sort").selectOption("Price (High - Low)");
        //page.getByTestId("product-price").first().waitFor();    //wait for the first product price to be visible after sort


        //Find all the prices on the page
        var productPrices =   page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map(WaitForAPIResponse::extractPrice)
                .toList();


        //It will have empty if we don't wait for API response

        System.out.println("Product Prices:" + productPrices);
        assertThat(productPrices).isNotEmpty().isSortedAccordingTo(Comparator.reverseOrder());


    }

    private static double extractPrice(String price){
        return Double.parseDouble(price.replace("$",""));
    }
}
