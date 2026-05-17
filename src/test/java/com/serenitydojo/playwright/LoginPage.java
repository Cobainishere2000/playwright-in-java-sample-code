package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page)
    {
        this.page = page;
    }

    public void open(){

    }

    public void Open() {
        page.navigate("https://practicesoftwaretesting.com/auth/login");
    }

    public void LoginAsUser(User user) {
        page.getByPlaceholder("Your email").fill(user.email());
        page.getByPlaceholder("Your Password").fill(user.password());
        page.locator(".btnSubmit").click();

    }

    public String Title() {
        return page.getByTestId("page-title").textContent();
    }
}