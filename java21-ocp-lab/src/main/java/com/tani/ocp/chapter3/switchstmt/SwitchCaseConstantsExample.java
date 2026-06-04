package com.tani.ocp.chapter3.switchstmt;

public class SwitchCaseConstantsExample {

    public static void main(String[] args) {
        System.out.println(describeAnimals(3));
        System.out.println(describeAnimals(15));
    }

    static String describeAnimals(int numberOfAnimals) {
        final int bananas = 1;
        return switch (numberOfAnimals) {
            case bananas -> "One banana for the monkey";
            case 3 * 5 -> "Fifteen animals in the exhibit";
            default -> "Other count";
        };
    }
}
