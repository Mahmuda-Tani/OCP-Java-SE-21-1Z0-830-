package com.tani.ocp.chapter3.loops;

public class DoWhileLoopExample {

    public static void main(String[] args) {
        int lizard = 0;
        do {
            lizard++;
        } while (false);
        System.out.println("do-while runs body at least once: " + lizard);
    }
}
