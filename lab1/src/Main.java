import a.Grammar;
import b.GrammarGenerator;
import c.FiniteAutomaton;
import c.GrammarToFAConverter;
import d.FiniteAutomatonD;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Usage of B");
        GrammarGenerator.generateValidStrings(5).forEach(System.out::println);
        System.out.println();

        System.out.println("Usage of C");
        Map<String, List<String>> productions = new HashMap<>();
        productions.put("S", Arrays.asList("aA", "bB"));
        productions.put("A", Arrays.asList("bS", "cA", "aB"));
        productions.put("B", Arrays.asList("aB", "b"));

        Grammar grammar = new Grammar(new HashSet<>(List.of("S", "A", "B")), new HashSet<>(List.of("a", "b", "c")), "S", productions);
        FiniteAutomaton convert = GrammarToFAConverter.convert(grammar);

        System.out.println(convert.getAlphabet());
        System.out.println(convert.getTransitions());
        System.out.println(convert.getStates());
        System.out.println();

        System.out.println("Usage of D");
        Set<String> states = new HashSet<>(Arrays.asList("S", "A", "B"));
        Set<String> acceptingStates = new HashSet<>(Arrays.asList("S", "B"));
        String startState = "S";
        Map<String, Map<Character, Set<String>>> transitions = new HashMap<>();
        transitions.put("S", new HashMap<>());
        transitions.get("S").put('a', new HashSet<>(Arrays.asList("A")));
        transitions.get("S").put('b', new HashSet<>(Arrays.asList("B")));
        transitions.put("A", new HashMap<>());
        transitions.get("A").put('b', new HashSet<>(Arrays.asList("S")));
        transitions.get("A").put('c', new HashSet<>(Arrays.asList("A")));
        transitions.get("A").put('a', new HashSet<>(Arrays.asList("B")));
        transitions.put("B", new HashMap<>());
        transitions.get("B").put('a', new HashSet<>(Arrays.asList("B")));
        transitions.get("B").put('b', new HashSet<>(Arrays.asList("B")));

        FiniteAutomatonD automaton = new FiniteAutomatonD(states, acceptingStates, startState, transitions);

        System.out.println(automaton.accepts("aab"));
        System.out.println(automaton.accepts("bb"));
        System.out.println(automaton.accepts("baaa"));
        System.out.println(automaton.accepts("caaa"));
        System.out.println(automaton.accepts("caba"));
    }
}
