package grammar;

import java.util.Map;
import java.util.Set;

public class StringChecker {
    private final Set<String> states;
    private final Set<String> acceptingStates;
    private final String startState;
    private final Map<String, Map<Character, Set<String>>> transitions;

    public StringChecker(Set<String> states, Set<String> acceptingStates, String startState,
                         Map<String, Map<Character, Set<String>>> transitions) {
        this.states = states;
        this.acceptingStates = acceptingStates;
        this.startState = startState;
        this.transitions = transitions;
    }

    public boolean accepts(String input) {
        String currentState = startState;
        for (char c : input.toCharArray()) {
            if (!transitions.containsKey(currentState) || !transitions.get(currentState).containsKey(c)) {
                return false;
            }
            currentState = transitions.get(currentState).get(c).iterator().next();
        }
        return acceptingStates.contains(currentState);
    }
}
