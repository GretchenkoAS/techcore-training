package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try(InputStream inputStream =Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        }

        System.out.println("DB: " + properties.getProperty("db.url"));
    }
}