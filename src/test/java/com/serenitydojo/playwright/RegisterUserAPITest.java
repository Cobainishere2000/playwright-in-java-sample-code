package com.serenitydojo.playwright;


import com.google.gson.Gson;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@UsePlaywright
public class RegisterUserAPITest {

    private APIRequestContext request;

    @BeforeEach
    void setup(Playwright playwright){
        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://api.practicesoftwaretesting.com")
        );
    }

    @AfterEach
    void teardown(){
        if (request != null){
            request.dispose();
        }
    }

    @Test
    void should_register_user(){
        User validUser = UserFactory.randomUser();

        var response = request.post("/users/register",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(validUser)
        );
        System.out.println(Arrays.toString(response.body()));
        System.out.println(validUser);
        assertThat(response.status()).isEqualTo(201);

        String response_body = response.text();
        Gson gson = new Gson();

        User createdUser = gson.fromJson(response_body, User.class);
        assertThat(createdUser).isEqualTo(validUser.withPassword(null));
    }

    @Test
    void first_name_is_mandatory(){
        User user_noname = UserFactory.randomUser(null);
        var response = request.post("/users/register",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(user_noname)
        );
        System.out.println(user_noname);
        //assertThat(response.status()).isEqualTo(201);

        String response_body = response.text();
        Gson gson = new Gson();
        System.out.println(response_body);
        assertThat(response.status()).isEqualTo(422);
    }
}