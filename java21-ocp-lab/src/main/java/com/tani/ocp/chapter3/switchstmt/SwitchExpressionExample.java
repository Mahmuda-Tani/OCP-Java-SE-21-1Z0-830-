package com.tani.ocp.chapter3.switchstmt;

public class SwitchExpressionExample {

    public static void main(String[] args) {

        int food = 5, month = 4, weather = 2, day = 0, time = 4;

        switch (day) { // #4
            case 1, 13: System.out.print("January");
            default:
            System.out.print("July");
            }

        System.out.println(getAnimal(4));
        System.out.println(getAnimal(3));
    }

    static String getAnimal(int type) {
        return switch (type) {
            case 0 -> "Lion";
            case 1 -> "Elephant";
            case 2, 3 -> "Alligator";
            case 4 -> "Crane";
            default -> "Unknown";
        };
    }
}
