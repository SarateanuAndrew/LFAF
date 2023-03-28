package automaton;

import java.util.Map;
import java.util.Set;

public class FiniteAutomaton {
    private final Set<String> states;
    private final Set<String> alphabet;
    private final String initialState;
    private final Set<String> finalStates;
    private final Map<String, Map<String, String>> transitions;

    public FiniteAutomaton(Set<String> states, Set<String> alphabet, String initialState, Set<String> finalStates, Map<String, Map<String, String>> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public Set<String> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }

    public Map<String, Map<String, String>> getTransitions() {
        return transitions;
    }
}
