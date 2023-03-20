import java.util.*;

public class Main {
    public static void main(String[] args) {
        //1
        Set<String> VN = new HashSet<>(Arrays.asList("S", "B", "C"));
        Set<String> VT = new HashSet<>(Arrays.asList("a", "b", "c"));
        Map<String, List<String>> P = new HashMap<>();
        P.put("S", List.of("aB"));
        P.put("B", Arrays.asList("aC", "bB"));
        P.put("C", Arrays.asList("bB", "c", "aS"));
        String classification = GrammarClassifier.classifyGrammar(VN, VT, P);
        System.out.println(classification);

        //2
        Set<String> states = new HashSet<>(Arrays.asList("q0", "q1", "q2"));
        Set<String> alphabet = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> finalStates = new HashSet<>(Collections.singletonList("q2"));
        Map<String, Map<String, Set<String>>> transitions = new HashMap<>();

        transitions.put("q0", new HashMap<>() {{
            put("a", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q0")));
        }});

        transitions.put("q1", new HashMap<>() {{
            put("a", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q2")));
        }});

        transitions.put("q2", new HashMap<>() {{
            put("a", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q2")));
        }});

        FiniteAutomaton fa = new FiniteAutomaton(states, alphabet, finalStates, transitions);

        // Convert to regular grammar and print
        fa.convertToRegularGrammar();

        // Determine whether deterministic or non-deterministic and print result
        System.out.println("Deterministic: " + fa.isDeterministic());

        //3
        fa.convert();

    }
}