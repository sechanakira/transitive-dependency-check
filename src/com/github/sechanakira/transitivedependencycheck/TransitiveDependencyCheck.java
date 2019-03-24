package com.github.sechanakira.transitivedependencycheck;

import java.util.List;

public interface TransitiveDependencyCheck {

    String findDependencies(String input);

    void register(String key, String... deps);

    List<String> getDependencies(String key);

    void print();

}
