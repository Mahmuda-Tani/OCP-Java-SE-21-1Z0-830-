package com.tani.ocp.chapter3.ifelse;

public class IfElseBasicsExample {

    public static void main(String[] args) {
        int hourOfDay = 14;

        if (hourOfDay < 11) {
            System.out.println("Good Morning");
        } else if (hourOfDay < 15) {
            System.out.println("Good Afternoon");
        } else {
            System.out.println("Good Evening");
        }
    }
}
