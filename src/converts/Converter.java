package converts;

import java.util.*;

public class Converter {
    private Set<String> states;
    private Set<String> alphabet;
    private Set<String> finalStates;
    private Map<String, Map<String, Set<String>>> transitions;

    public Converter(Set<String> states, Set<String> alphabet, Set<String> finalStates,
                     Map<String, Map<String, Set<String>>> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    // Function to convert finite automaton to regular grammar
    public void convertToRegularGrammar() {
        for (String state : states) {
            System.out.println(state + " -> " + getProductions(state));
        }
    }

    private Set<String> getProductions(String state) {
        Set<String> productions = new HashSet<>();
        Map<String, Set<String>> transitionMap = transitions.get(state);

        // If final state, add epsilon production
        if (finalStates.contains(state)) {
            productions.add("ε");
        }

        for (String symbol : transitionMap.keySet()) {
            for (String nextState : transitionMap.get(symbol)) {
                if (nextState.equals(state)) {
                    productions.add(symbol);
                } else {
                    productions.add(symbol + getProductionSuffix(nextState));
                }
            }
        }

        return productions;
    }

    private String getProductionSuffix(String state) {
        String suffix = "";
        Map<String, Set<String>> transitionMap = transitions.get(state);

        if (finalStates.contains(state)) {
            suffix += "ε";
        }

        for (String symbol : transitionMap.keySet()) {
            for (String nextState : transitionMap.get(symbol)) {
                if (nextState.equals(state)) {
                    suffix += symbol;
                } else {
                    suffix += symbol + getProductionSuffix(nextState);
                }
            }
        }

        return suffix;
    }

    // Function to determine whether finite automaton is deterministic or non-deterministic
    public boolean isDeterministic() {
        for (String state : transitions.keySet()) {
            Map<String, Set<String>> transitionMap = transitions.get(state);

            for (String symbol : transitionMap.keySet()) {
                if (transitionMap.get(symbol).size() != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    //converter
    public void convert() {
        Set<String> dfaStates = new HashSet<>();
        Map<String, Map<String, Set<String>>> dfaTransitions = new HashMap<>();

        Set<String> initialStates = new HashSet<>();
        initialStates.add("q0");
        initialStates = epsilonClosure(initialStates);

        dfaStates.add(setToString(initialStates));

        Queue<Set<String>> unprocessedStates = new LinkedList<>();
        unprocessedStates.add(initialStates);

        while (!unprocessedStates.isEmpty()) {
            Set<String> currentStates = unprocessedStates.poll();

            for (String symbol : alphabet) {
                Set<String> nextStates = new HashSet<>();
                for (String state : currentStates) {
                    if (transitions.containsKey(state) && transitions.get(state).containsKey(symbol)) {
                        nextStates.addAll(transitions.get(state).get(symbol));
                    }
                }
                nextStates = epsilonClosure(nextStates);

                if (!nextStates.isEmpty()) {
                    String nextStateString = setToString(nextStates);
                    if (!dfaStates.contains(nextStateString)) {
                        unprocessedStates.add(nextStates);
                        dfaStates.add(nextStateString);
                    }

                    if (!dfaTransitions.containsKey(setToString(currentStates))) {
                        dfaTransitions.put(setToString(currentStates), new HashMap<>());
                    }
                    dfaTransitions.get(setToString(currentStates)).put(symbol, nextStates);

                    if (nextStates.stream().anyMatch(finalStates::contains)) {
                        finalStates.add(nextStateString);
                    }
                }
            }
        }

        states = dfaStates;
        transitions = dfaTransitions;
    }

    private Set<String> epsilonClosure(Set<String> states) {
        Set<String> closure = new HashSet<>(states);
        Queue<String> unprocessedStates = new LinkedList<>(states);

        while (!unprocessedStates.isEmpty()) {
            String currentState = unprocessedStates.poll();

            if (transitions.containsKey(currentState) && transitions.get(currentState).containsKey("")) {
                Set<String> nextStates = transitions.get(currentState).get("");
                for (String nextState : nextStates) {
                    if (!closure.contains(nextState)) {
                        closure.add(nextState);
                        unprocessedStates.add(nextState);
                    }
                }
            }
        }

        return closure;
    }

    private String setToString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (String element : set) {
            if (!first) {
                sb.append(",");
            }
            sb.append(element);
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }
}
