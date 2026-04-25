package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

@UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright.class)
public class TryAssertj {

    @Test
    void allProductPricesShouldBeValid(Page page){

        page.navigate("https://practicesoftwaretesting.com");
        page.waitForCondition(() -> page.getByTestId("product-name").count()>0);

        List<Double> prices = page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$","")))
                .toList();

        assertThat(prices).isNotEmpty()
                .allMatch(price -> price>0)
                .doesNotContain(0.0)
                .allMatch(price -> price<1000)
                .allSatisfy(price -> assertThat(price)
                        .isGreaterThan(0.0).isLessThan(1000.0));

    }

    @Test
    void ShouldShowSortedAlphabetically(Page page){

        page.navigate("https://practicesoftwaretesting.com");
        page.waitForCondition(() -> page.getByTestId("product-name").count()>0);

        page.getByLabel("Sort").selectOption("Name (A - Z)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<String> ProductNames = page.getByTestId("product-name").allTextContents();

        assertThat(ProductNames).isNotEmpty()
                .isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);

        System.out.println(ProductNames);

    }
}
