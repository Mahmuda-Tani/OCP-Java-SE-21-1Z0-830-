package com.tani.ocp.chapter3.loops;

public class WhileLoopExample {

    public static void main(String[] args) {
        int counter = 0;
        while (counter < 5) {
            System.out.print(counter + " ");
            counter++;
        }
        System.out.println();

        int roomInBelly = 5;
        int bitesOfCheese = 8;
        while (bitesOfCheese > 0 && roomInBelly > 0) {
            bitesOfCheese--;
            roomInBelly--;
        }
        System.out.println("Cheese left: " + bitesOfCheese + ", belly room: " + roomInBelly);
    }
}
