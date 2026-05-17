package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAPIClient {

    private final Page page;
    private static final String REGISTRATION_URL="https://api-with-bugs.practicesoftwaretesting.com/users/register";
    public UserAPIClient(Page page){
        this.page = page;
    }

    public void RegisterUser(User user) {
        //'https://api-with-bugs.practicesoftwaretesting.com/users/register'

        var response = page.request().post(
                REGISTRATION_URL,
                RequestOptions.create().setData(user)
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json"));

        System.out.println(Arrays.toString(response.body()));
        System.out.println(user);
        System.out.println(response.text());


        if(response.status() != 201)
        {
            throw new IllegalStateException("Could not create User: " + response.text() );
        }

    }
}
