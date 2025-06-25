package constants;

import net.datafaker.Faker;


public class RandomData {
    static Faker faker = new Faker();

    public static String email(){
        return faker.name().username() + faker.random().nextInt(100) + "@mail.ru";
    }

    public static String password(){
        return faker.internet().password();
    }
}
