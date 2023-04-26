import automaton.FiniteAutomaton;
import automaton.GrammarClassifier;
import chomsky.Chomsky;
import converts.Converter;
import converts.GrammarToFAConverter;
import grammar.Grammar;
import grammar.GrammarGenerator;
import grammar.StringChecker;
import lexer.Token;

import java.util.*;

import static lexer.Lexer.tokenize;

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

        Grammar grammar = new Grammar(new HashSet<>(List.of("S", "A", "B")), new HashSet<>(List.of("grammar", "b", "c")), "S", productions);
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

        StringChecker automaton = new StringChecker(states, acceptingStates, startState, transitions);

        System.out.println(automaton.accepts("aab"));
        System.out.println(automaton.accepts("bb"));
        System.out.println(automaton.accepts("baaa"));
        System.out.println(automaton.accepts("caaa"));
        System.out.println(automaton.accepts("caba"));

        //1
        Set<String> VN = new HashSet<>(Arrays.asList("S", "B", "C"));
        Set<String> VT = new HashSet<>(Arrays.asList("grammar", "b", "c"));
        Map<String, List<String>> P = new HashMap<>();
        P.put("S", List.of("aB"));
        P.put("B", Arrays.asList("aC", "bB"));
        P.put("C", Arrays.asList("bB", "c", "aS"));
        String classification = GrammarClassifier.classifyGrammar(VN, VT, P);
        System.out.println(classification);

        //2
        Set<String> states2 = new HashSet<>(Arrays.asList("q0", "q1", "q2"));
        Set<String> alphabet = new HashSet<>(Arrays.asList("grammar", "b"));
        Set<String> finalStates = new HashSet<>(Collections.singletonList("q2"));
        Map<String, Map<String, Set<String>>> transitions2 = new HashMap<>();

        transitions2.put("q0", new HashMap<>() {{
            put("grammar", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q0")));
        }});

        transitions2.put("q1", new HashMap<>() {{
            put("grammar", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q2")));
        }});

        transitions2.put("q2", new HashMap<>() {{
            put("grammar", new HashSet<>(Collections.singletonList("q0")));
            put("b", new HashSet<>(Collections.singletonList("q2")));
        }});

        Converter fa = new Converter(states2, alphabet, finalStates, transitions2);

        // Convert to regular grammar and print
        fa.convertToRegularGrammar();

        // Determine whether deterministic or non-deterministic and print result
        System.out.println("Deterministic: " + fa.isDeterministic());

        //3
        fa.convert();

        //Check Lexer
        System.out.println("Check Lexer");
        ArrayList<Token> tokens = tokenize("x+12+42*y-z/2");
        for (Token token : tokens) {
            System.out.println(token);
        }

        //Chomsky Normal Form
        System.out.println();
        System.out.println("Chomsky Normal Form:");


        Chomsky chomsky = new Chomsky("S",
                List.of("S", "A", "B", "C", "E"),
                List.of("a", "b"),
                new HashMap<>() {{
                    put("S", List.of("bAC", "B"));
                    put("A", List.of("a", "aS", "bCaCb"));
                    put("B", List.of("AC", "bS", "aAa"));
                    put("C", List.of("ε", "AB"));
                    put("E", List.of("BA"));
                }});

//        System.out.println("Original:");
//        chomsky.printGrammar();

        chomsky.eliminateEpsilonProductions();
        System.out.println("Epsilon:");
        chomsky.printGrammar();

        chomsky.eliminateUnitProductions();
        System.out.println("Unit productions:");
        chomsky.printGrammar();

        chomsky.eliminateInaccessibleSymbols();
        System.out.println("Inaccessible Symbols:");
        chomsky.printGrammar();

        chomsky.eliminateNonproductive();
        System.out.println("Nonproductive Characters:");
        chomsky.printGrammar();

        chomsky.toCnf();
        System.out.println("Chomsky Normal Form:");
        chomsky.printGrammar();
    }
}