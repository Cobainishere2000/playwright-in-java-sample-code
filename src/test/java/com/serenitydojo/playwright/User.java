package com.serenitydojo.playwright;

/*

  "first_name": "John",
  "last_name": "Doe",
  "address": {
    "street": "Street 1",
    "house_number": "12",
    "city": "City",
    "state": "State",
    "country": "Country",
    "postal_code": "1234AA"
  },
  "phone": "0987654321",
  "dob": "1970-01-01",
  "password": "SuperSecure@123",
  "email": "john@doe.example"

 */

public record User(
        String first_name,
        String last_name,
        //Address address,
        String address,
        String city,
        String state,
        String country,
        String postal_code,
        String phone,
        String dob,
        String password,
        String email

) {
    public Object withPassword(String password) {
        return new User(
                first_name,
                last_name,
                address,
                city,
                state,
                country,
                postal_code,
                phone,
                dob,
                password,
                email
        );
    }

    public void PrintDetails() {
        System.out.println("First Name:"+ first_name);
        System.out.println("Last Name:"+ last_name);
        System.out.println("Address Name:"+ address);
        System.out.println("City:"+ city);
        System.out.println("State:"+ state);
        System.out.println("Country:"+ country);
        System.out.println("Zip:"+ postal_code);
        System.out.println("Phone:" + phone);
        System.out.println("DOB:"+ dob);
        System.out.println("Password"+ password);
        System.out.println("Email :"+ email);

    }
}
