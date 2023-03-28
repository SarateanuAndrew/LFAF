package converts;

import automaton.FiniteAutomaton;
import grammar.Grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrammarToFAConverter {
    public static FiniteAutomaton convert(Grammar g) {
        // Create a set of states for the FA
        Set<String> states = new HashSet<>();
        states.add(g.getS());
        for (String vn : g.getVn()) {
            for (String s : g.getP().get(vn)) {
                for (int i = 0; i < s.length(); i++) {
                    if (g.getVt().contains(s.charAt(i) + "")) {
                        states.add(s.substring(0, i) + s.substring(i + 1));
                    }
                }
            }
        }

        // Create a set of final states for the FA
        Set<String> finalStates = new HashSet<>();
        for (String state : states) {
            if (state.indexOf(g.getS()) != -1) {
                finalStates.add(state);
            }
        }

        // Create a map of transitions for the FA
        Map<String, Map<String, String>> transitions = new HashMap<>();
        for (String state : states) {
            Map<String, String> transitionMap = new HashMap<>();
            for (String a : g.getVt()) {
                String newState = "";
                for (int i = 0; i < state.length(); i++) {
                    if (g.getVt().contains(state.charAt(i) + "")) {
                        newState += state.charAt(i);
                    } else {
                        newState += g.getP().get(state.charAt(i) + "").get(0);
                    }
                }
                newState = newState.replaceAll(a, "");
                transitionMap.put(a, newState);
            }
            transitions.put(state, transitionMap);
        }

        return new FiniteAutomaton(states, g.getVt(), g.getS(), finalStates, transitions);
    }
}
