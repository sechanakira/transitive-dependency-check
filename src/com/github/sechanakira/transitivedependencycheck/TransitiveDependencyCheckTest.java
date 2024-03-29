package com.github.sechanakira.transitivedependencycheck;

public class TransitiveDependencyCheckTest {

    private static final String INPUT = "A B C\n" +
            "B C E \n" +
            "C G\n" +
            "D A F\n" +
            "E F\n" +
            "F H";

    private static final String EXPECTED_OUTPUT = "ABCEFGH\n" +
            "BCEFGH\n" +
            "CG\n" +
            "DABCEFGH\n" +
            "EFH\n" +
            "FH\n";

    private static final String EMPTY_OUTPUT = "";

    public void testFindDependencies() {
        TransitiveDependencyCheck dependencyCheck = new TransitiveDependencyCheckImpl();
        String output = dependencyCheck.findDependencies(INPUT);
        assert EXPECTED_OUTPUT.equals(output) : "Error calculating dependencies";
    }

    public void testFindInverseDependencies() {
        TransitiveDependencyCheck dependencyCheck = new TransitiveDependencyCheckImpl();
        String output = dependencyCheck.findInverseDependencies(INPUT);
        assert EMPTY_OUTPUT.equals(output) : "Error calculating inverse dependencies";
    }
}
