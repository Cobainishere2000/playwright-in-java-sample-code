package com.serenitydojo.playwright.toolshop.catalog.pageobjects;

import com.microsoft.playwright.Page;
import net.serenitybdd.annotations.Step;

import java.util.List;

public class AddressForm {
    private final Page page;

    public AddressForm(Page page) {
        this.page = page;
    }

    @Step("Confirm billing address")
    public void confirmAddress() {
        page.getByTestId("proceed-3").click();
    }
}