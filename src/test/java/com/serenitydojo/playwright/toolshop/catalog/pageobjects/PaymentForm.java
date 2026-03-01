package com.serenitydojo.playwright.toolshop.catalog.pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import net.serenitybdd.annotations.Step;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PaymentForm {
    private final Page page;

    public PaymentForm(Page page) {
        this.page = page;
    }

    @Step("Choose payment method '{0}'")
    public void choosePaymentMethod(String paymentMethod) {
        choosePaymentMethod(paymentMethod, new HashMap<>());
    }

    @Step("Choose payment method '{0}' with details")
    public void choosePaymentMethod(String paymentMethod, Map<String,String> paymentDetails) {
        page.getByTestId("payment-method").selectOption(paymentMethod);

        // Complete details if required for payment type

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

        assertThat(page.locator(".alert-success")).hasText("Payment was successful");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

    }
}