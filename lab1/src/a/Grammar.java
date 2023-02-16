package a;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grammar {
    private Map<Character, List<String>> productions;
    private Set<Character> nonterminals;
    private Set<Character> terminals;
    private char startSymbol;

    public Grammar(Map<Character, List<String>> productions, Set<Character> nonterminals,
                   Set<Character> terminals, char startSymbol) {
        this.productions = productions;
        this.nonterminals = nonterminals;
        this.terminals = terminals;
        this.startSymbol = startSymbol;
    }

    public Map<Character, List<String>> getProductions() {
        return productions;
    }

    public Set<Character> getNonterminals() {
        return nonterminals;
    }

    public Set<Character> getTerminals() {
        return terminals;
    }

    public char getStartSymbol() {
        return startSymbol;
    }
}
