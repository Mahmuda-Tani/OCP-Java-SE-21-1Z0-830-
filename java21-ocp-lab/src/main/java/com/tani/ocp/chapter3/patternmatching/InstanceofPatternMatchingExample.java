package com.tani.ocp.chapter3.patternmatching;

public class InstanceofPatternMatchingExample {

    public static void main(String[] args) {


        String noObjectHere = null;
        if(noObjectHere instanceof String)
        System.out.println("Not printed");
        if(noObjectHere instanceof String s)
        System.out.println("Still not printed");
        if(noObjectHere instanceof String s && s != null && s.length() > -1)
        System.out.println("Nope, not this one either");
    

        compareIntegers(5);
        compareIntegers(10);
        compareIntegers(null);
    }

    static void compareIntegers(Number number) {
        if (number instanceof Integer data && data.compareTo(5) > 0) {
            System.out.println("Greater than 5: " + data);
        } else if (number instanceof Integer data) {
            System.out.println("Integer <= 5: " + data);
        } else {
            System.out.println("Not an Integer: " + number);
        }
    }
}
