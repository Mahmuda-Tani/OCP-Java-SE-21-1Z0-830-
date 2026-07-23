package com.tani.ocp.chapter3.patternmatching;

public class FlowScopingExample {

    public static void main(String[] args) {
        printOnlyIntegers(42);
        printOnlyIntegers(3.14);
    }

    static void printOnlyIntegers(Number number) {
        if (!(number instanceof Integer data)) {
            System.out.println("Skipping non-integer: " + number);
            return;
        }
        // Compiler knows data is Integer here (flow scoping).
        System.out.println("Integer value: " + data.intValue());
    }
}
