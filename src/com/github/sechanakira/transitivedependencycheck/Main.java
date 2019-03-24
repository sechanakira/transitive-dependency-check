package com.github.sechanakira.transitivedependencycheck;

public class Main {

    private static final String INPUT = "A B C\n" +
            "B C E \n" +
            "C G\n" +
            "D A F\n" +
            "E F\n" +
            "F H";

    public static void main(String... args) {
        TransitiveDependencyCheck dependencyCheck = new TransitiveDependencyCheckImpl();
        String output = dependencyCheck.findDependencies(INPUT);
        System.out.println(output);
    }
}
