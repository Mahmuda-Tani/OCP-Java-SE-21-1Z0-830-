package com.tani.ocp.chapter3.loops;

public class ForLoopExample {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (var counter = 4; counter >= 0; counter--) {
            System.out.print(counter + " ");
        }
        System.out.println();
    }
}
