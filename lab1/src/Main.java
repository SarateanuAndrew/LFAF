import a.Grammar;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Character, List<String>> productions = new HashMap<>();
        productions.put('S', Arrays.asList("aA", "bB"));
        productions.put('A', Arrays.asList("bS", "cA", "aB"));
        productions.put('B', Arrays.asList("aB", "b"));

        Set<Character> nonterminals = new HashSet<>(Arrays.asList('S', 'A', 'B'));
        Set<Character> terminals = new HashSet<>(Arrays.asList('a', 'b', 'c'));

        char startSymbol = 'S';

        Grammar grammar = new Grammar(productions, nonterminals, terminals, startSymbol);
    }
}