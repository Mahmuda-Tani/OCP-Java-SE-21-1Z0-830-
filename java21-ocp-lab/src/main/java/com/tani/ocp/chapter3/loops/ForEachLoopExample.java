package com.tani.ocp.chapter3.loops;

import java.util.List;

public class ForEachLoopExample {

    public static void main(String[] args) {
        String[] habitats = { "Savanna", "Jungle", "Aquarium" };
        for (String habitat : habitats) {
            System.out.println("Habitat: " + habitat);
        }

        List<String> animals = List.of("Lion", "Elephant", "Crane");
        for (String animal : animals) {
            System.out.println("Animal: " + animal);
        }
    }
}
