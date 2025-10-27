package org.example;

import com.google.common.base.Strings;

public class Main {
    public static void main(String[] args) {
        String nullString = null;
        String notNullString = "Andrew";
        System.out.println("is null or empty: " + Strings.isNullOrEmpty(nullString));
        System.out.println("is null or empty: " + Strings.isNullOrEmpty(notNullString));
    }
}