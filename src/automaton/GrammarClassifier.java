package automaton;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrammarClassifier {
    public static String classifyGrammar(Set<String> VN, Set<String> VT, Map<String, List<String>> P) {
        // Step 1: Check if grammar is regular
        boolean isRegular = true;
        for (String nonterminal : VN) {
            for (String production : P.get(nonterminal)) {
                if (production.length() > 2 || (production.length() == 2 && !VT.contains(production.charAt(1) + ""))) {
                    isRegular = false;
                    break;
                }
            }
        }
        if (isRegular) {
            return "Regular grammar";
        }

        // Step 2: Check if grammar is context-free
        boolean isContextFree = true;
        for (String nonterminal : VN) {
            for (String production : P.get(nonterminal)) {
                if (production.length() > 1 || (production.length() == 1 && !VT.contains(production))) {
                    isContextFree = false;
                    break;
                }
            }
        }
        if (isContextFree) {
            return "Context-free grammar";
        }

        // Step 3: Check if grammar is context-sensitive
        boolean isContextSensitive = true;
        for (String nonterminal : VN) {
            for (String production : P.get(nonterminal)) {
                if (production.length() < 1 || !VN.contains(production.charAt(0) + "")) {
                    isContextSensitive = false;
                    break;
                }
            }
        }
        if (isContextSensitive) {
            return "Context-sensitive grammar";
        }

        // Step 4: Grammar is unrestricted
        return "Unrestricted grammar";
    }
}
