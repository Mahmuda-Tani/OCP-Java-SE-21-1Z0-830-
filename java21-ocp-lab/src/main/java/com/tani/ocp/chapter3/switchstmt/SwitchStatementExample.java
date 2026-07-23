package com.tani.ocp.chapter3.switchstmt;

public class SwitchStatementExample {

    public static void main(String[] args) {
        // System.out.println(getAnimal(0));
        // System.out.println(getAnimal(2));
        // System.out.println(getAnimal(99));
        printSeasonForMonth(2);
    }

    static String getAnimal(int type) {
        String animal;
        switch (type) {
            case 0:
                animal = "Lion";
                break;
            case 1:
                animal = "Elephant";
                break;
            case 2, 3:
                animal = "Alligator";
                break;
            default:
                animal = "Unknown";
        }
        return animal;
    }

    static void printSeasonForMonth(int month) {
        String value = switch (month) {
        case 1, 2, 3
        -> "Winter-";
        case 4, 5, 6
        -> "Spring-";
        default
        -> "Unknown-";
        case 7, 8, 9
        -> "Summer-";
        case 10, 11, 12 -> "Fall-";
        };
        System.out.print(value);
        }
}
