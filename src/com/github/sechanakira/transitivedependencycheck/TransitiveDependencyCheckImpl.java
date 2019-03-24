package com.github.sechanakira.transitivedependencycheck;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TransitiveDependencyCheckImpl implements TransitiveDependencyCheck {

    private Map<String, List<String>> dependenciesHolder = new HashMap<>();

    private List<String> res = new ArrayList<>();

    private Logger logger = Logger.getLogger(TransitiveDependencyCheckImpl.class.getName());

    @Override
    public String findDependencies(String input) {
        Arrays.stream(input.split("\\n")).forEach(line -> {
            String[] items = line.split("");
            String key = items[0];
            String[] deps = new String[items.length - 1];
            for (int i = 1; i < items.length; i++) {
                deps[i - 1] = items[i];
            }
            register(key, deps);
        });
        return this.toString();
    }

    @Override
    public void register(String key, String... deps) {
        for (String dep : deps) {
            if (!checkDependent(dep, key)) {
                dependenciesHolder.put(key, Arrays.asList(deps));
            }
        }
    }

    private boolean checkDependent(String dep, String item) {
        boolean isDependent = false;
        List<String> deps = getDependencies(dep);

        for (String e : deps) {
            isDependent = e.equals(item);
        }
        return isDependent;
    }

    @Override
    public List<String> getDependencies(String key) {
        List<String> deps = dependenciesHolder.get(key);
        if (deps == null) {
            return getSorted();
        }

        for (String dep : deps) {
            recourse(dep);
        }

        return getSorted();
    }

    private void recourse(String key) {
        if (!res.contains(key)) {
            res.add(key);
        }

        List<String> deps = dependenciesHolder.get(key);
        if (deps == null) {
            return;
        }

        for (String dep : deps) {
            recourse(dep);
        }
    }

    private List<String> getSorted() {
        String[] toBeSorted = res.toArray(new String[0]);
        res = new ArrayList<>();
        Arrays.sort(toBeSorted);
        return Arrays.asList(toBeSorted);
    }

    @Override
    public void print() {
        for (String key : dependenciesHolder.keySet()) {
            List<String> deps = getDependencies(key);
            logger.info(key + " depends on: " + deps.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : dependenciesHolder.keySet()) {
            List<String> deps = getDependencies(key);
            sb.append(key);
            sb.append(deps.stream().collect(Collectors.joining()));
            sb.append("\n");
        }
        return sb.toString().replaceAll(" ","");
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof TransitiveDependencyCheckImpl)) {
            return false;
        }
        return this.toString().trim().equalsIgnoreCase(other.toString().trim());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

