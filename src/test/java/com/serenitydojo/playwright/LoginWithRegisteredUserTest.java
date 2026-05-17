package com.serenitydojo.playwright;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LoginWithRegisteredUserTest extends PlaywrightTestCase {

    @Test
    @DisplayName("Should be able to login with registered used")
    void should_login_with_registered_user(){

        //Register a user by API
        User user = UserFactory.randomUser();
        user.PrintDetails();
        UserAPIClient userapiclient = new UserAPIClient(page);
        userapiclient.RegisterUser(user);

        //Login via the login page
        LoginPage loginpage = new LoginPage(page);
        loginpage.Open();
        loginpage.LoginAsUser(user);

        //Check that we are on the right page
        assertThat(loginpage.Title()).isEqualTo("My account");

    }
}
