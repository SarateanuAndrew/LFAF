package tests;

import chomsky.Chomsky;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestChomsky {
    Chomsky chomsky;

    @Before
    public void beforeTests() {
        chomsky = new Chomsky("S",
                Arrays.asList("S", "A", "B", "C", "E"),
                Arrays.asList("a", "b"),
                new HashMap<>() {{
                    put("S", List.of("bAC", "B"));
                    put("A", List.of("a", "aS", "bCaCb"));
                    put("B", List.of("AC", "bS", "aAa"));
                    put("C", List.of("ε", "AB"));
                    put("E", List.of("BA"));
                }});
    }

    @Test
    public void testEliminateEpsilonProductions() {

        chomsky.eliminateEpsilonProductions();

        Map<String, List<String>> expected = new HashMap<>() {{
            put("S", List.of("bA", "bAC", "B"));
            put("A", List.of("a", "aS", "bab", "bCaCb"));
            put("B", List.of("A", "AC", "bS", "aAa"));
            put("C", List.of("AB"));
            put("E", List.of("BA"));
        }};

        assertEquals(expected, chomsky.getProductions());
    }

    @Test
    public void testEliminateInaccessibleSymbols1() {
        chomsky.eliminateEpsilonProductions();
        chomsky.eliminateUnitProductions();
        chomsky.eliminateInaccessibleSymbols();

        Map<String, List<String>> expected = new HashMap<>() {{
            put("S", List.of("bA", "bAC", "AC", "bS", "aAa", "a", "aS", "bab", "bCaCb"));
            put("A", List.of("a", "aS", "bab", "bCaCb"));
            put("B", List.of("AC", "bS", "aAa", "a", "aS", "bab", "bCaCb"));
            put("C", List.of("AB"));
        }};

        assertEquals(expected, chomsky.getProductions());
    }

    @Test
    public void testEliminateNonproductive() {
        chomsky.eliminateEpsilonProductions();
        chomsky.eliminateUnitProductions();
        chomsky.eliminateInaccessibleSymbols();
        chomsky.eliminateNonproductive();

        Map<String, List<String>> expected = new HashMap<>() {{
            put("S", List.of("bA", "bAC", "AC", "bS", "aAa", "a", "aS", "bab", "bCaCb"));
            put("A", List.of("a", "aS", "bab", "bCaCb"));
            put("B", List.of("AC", "bS", "aAa", "a", "aS", "bab", "bCaCb"));
            put("C", List.of("AB"));
        }};

        assertEquals(expected, chomsky.getProductions());
    }
}
