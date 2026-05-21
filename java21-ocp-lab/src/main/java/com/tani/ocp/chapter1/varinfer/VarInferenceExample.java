package com.tani.ocp.chapter1.varinfer;

public class VarInferenceExample {

    public static void main(String[] args) {
        // 'var' infers type at compile time from initializer.
        var topic = "Local variable type inference";
        System.out.println("Topic: " + topic);
    }
}
