package com.serenitydojo.playwright;
import net.datafaker.Faker;

public class UserFactory {
    public static User randomUser() {

        Faker faker = new Faker();
        /*
        Address address = new Address(
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().country(),
                faker.regexify("[0-9]{4}[A-Z]{2}")
        );

         */

        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().streetName()+" " +faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().country(),
                faker.regexify("[0-9]{4}[A-Z]{2}"),
                faker.phoneNumber().subscriberNumber(10),
                "1970-01-01",
                faker.credentials().password(8,12,true,true),
                faker.internet().emailAddress()
        );
    }

    public static User randomUser(String Empty) {

        Faker faker = new Faker();

        /*
        Address address = new Address(
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().country(),
                faker.regexify("[0-9]{4}[A-Z]{2}")
        );

         */

        return new User(
                Empty,
                faker.name().lastName(),
                faker.address().streetName()+" " +faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().country(),
                faker.regexify("[0-9]{4}[A-Z]{2}"),
                faker.phoneNumber().subscriberNumber(10),
                "1970-01-01",
                "SuperSecure@123",
                faker.internet().emailAddress()
        );
    }
}
