package com.tani.ocp.chapter3.branching;

public class LabeledBreakContinueExample {

    public static void main(String[] args) {
        CLEANING:
        for (char stable = 'a'; stable <= 'd'; stable++) {
            for (int leopard = 1; leopard <= 3; leopard++) {
                if (stable == 'b' || leopard == 2) {
                    continue CLEANING;
                }
                System.out.println("Cleaning: " + stable + "," + leopard);
            }
        }
    }
}
