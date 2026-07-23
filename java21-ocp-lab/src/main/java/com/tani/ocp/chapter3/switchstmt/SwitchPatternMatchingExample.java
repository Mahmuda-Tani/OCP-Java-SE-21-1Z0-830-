package com.tani.ocp.chapter3.switchstmt;

public class SwitchPatternMatchingExample {

    public static void main(String[] args) {
        System.out.println(getTrainer(12));
        System.out.println(getTrainer(7));
        System.out.println(getTrainer(14.0));
        System.out.println(getTrainer((short) 5));
    }

    static String getTrainer(Number height) {
        return switch (height) {
            case Integer i when i > 10 -> "Joseph";
            case Integer i -> "Daniel";
            case Double num when num <= 15.5 -> "Peter";
            case Double num -> "Kelly";
            case Number num -> "Ralph";
        };
    }
}
