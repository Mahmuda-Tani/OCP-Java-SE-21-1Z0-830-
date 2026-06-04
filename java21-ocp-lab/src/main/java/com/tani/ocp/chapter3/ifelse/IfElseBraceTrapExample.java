package com.tani.ocp.chapter3.ifelse;

public class IfElseBraceTrapExample {

    public static void main(String[] args) {
        int hourOfDay = 8;
        int morningGreetingCount = 0;

        // Only println is inside the if; increment ALWAYS runs.
        if (hourOfDay < 11)
            System.out.println("Good Morning");
        morningGreetingCount++;

        System.out.println("Greeting count: " + morningGreetingCount);
    }
}
