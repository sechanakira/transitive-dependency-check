package com.github.sechanakira.transitivedependencycheck;

public class Main {

    private static final String INPUT = "A B C\n" +
            "B C E \n" +
            "C G\n" +
            "D A F\n" +
            "E F\n" +
            "F H";

    public static void main(String... args) {
        runTests();
        TransitiveDependencyCheck dependencyCheck = new TransitiveDependencyCheckImpl();
        String output = dependencyCheck.findDependencies(INPUT);
        System.out.println(output);
        dependencyCheck.findInverseDependencies(INPUT);
    }

    private static void runTests() {
        TransitiveDependencyCheckTest dependencyCheckTest = new TransitiveDependencyCheckTest();
        dependencyCheckTest.testFindDependencies();
        dependencyCheckTest.testFindInverseDependencies();
    }
}
