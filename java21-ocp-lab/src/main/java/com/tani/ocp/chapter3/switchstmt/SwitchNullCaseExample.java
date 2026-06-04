package com.tani.ocp.chapter3.switchstmt;

public class SwitchNullCaseExample {

    public static void main(String[] args) {
        System.out.println(greetFish(null));
        System.out.println(greetFish("ClownFish"));
        System.out.println(greetFish("Salmon"));
    }

    static String greetFish(String fish) {
        return switch (fish) {
            case "ClownFish" -> "Hello!";
            case "BlueTang" -> "Hello again!";
            case null -> "What type of fish are you?";
            default -> "Goodbye";
        };
    }
}
