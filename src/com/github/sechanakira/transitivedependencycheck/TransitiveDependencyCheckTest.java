package com.github.sechanakira.transitivedependencycheck;

public class TransitiveDependencyCheckTest {

    private static String INPUT = "A B C\n" +
            "B C E \n" +
            "C G\n" +
            "D A F\n" +
            "E F\n" +
            "F H";

    private static String EXPECTED_OUTPUT = "ABCEFGH\n" +
            "BCEFGH\n" +
            "CG\n" +
            "DABCEFGH\n" +
            "EFH\n" +
            "FH\n";

    public void testFindDependencies(){
        TransitiveDependencyCheck dependencyCheck = new TransitiveDependencyCheckImpl();
        String output = dependencyCheck.findDependencies(INPUT);
        assert EXPECTED_OUTPUT.equals(output) : "Error calculatinng dependencies";
    }
}
