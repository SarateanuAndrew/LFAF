# LFAF

## Student: Sarateanu Andrei

----

## Theory

&ensp;&ensp;&ensp; A finite automaton is a mechanism used to represent processes of different kinds. It can be compared
to a state machine as they both have similar structures and purpose as well. The word finite signifies the fact that an
automaton comes with a starting and a set of final states. In other words, for process modeled by an automaton has a
beginning and an ending.

&ensp;&ensp;&ensp; Based on the structure of an automaton, there are cases in which with one transition multiple states
can be reached which causes non determinism to appear. In general, when talking about systems theory the word
determinism characterizes how predictable a system is. If there are random variables involved, the system becomes
stochastic or non deterministic.

&ensp;&ensp;&ensp; That being said, the automata can be classified as non-/deterministic, and there is in fact a
possibility to reach determinism by following algorithms which modify the structure of the automaton.

## Objectives:

1. Understand what an automaton is and what it can be used for.

2. Continuing the work in the same repository and the same project, the following need to be added:
   a. Provide a function in your grammar type/class that could classify the grammar based on Chomsky hierarchy.

   b. For this you can use the variant from the previous lab.

3. According to your variant number (by universal convention it is register ID), get the finite automaton definition and
   do the following tasks:

   a. Implement conversion of a finite automaton to a regular grammar.

   b. Determine whether your FA is deterministic or non-deterministic.

   c. Implement some functionality that would convert an NDFA to a DFA.

   d. Represent the finite automaton graphically (Optional, and can be considered as a __*bonus point*__):

    - You can use external libraries, tools or APIs to generate the figures/diagrams.

    - Your program needs to gather and send the data about the automaton and the lib/tool/API return the visual
      representation.


## Chomsky hierarchy:

1) Unrestricted grammar
2) Context-sensitive grammars
3) Context-free grammars
4) Regular grammars

I defined a function classifyGrammar that takes three parameters: VN (the set of non-terminals), VT (the set of
terminals), and P (the set of productions). The function first checks if the grammar is regular by ensuring that each
production is of the form A → aB or A → a, where A is a non-terminal, a is a terminal, and B is an optional
non-terminal. If the grammar is regular, we return "Regular grammar". If not, we proceed to check if the grammar is
context-free by ensuring that each production is of the form A → α, where A is a non-terminal and α is a string of
terminals and non-terminals. If the grammar is context-free, we return "Context-free grammar". If not, we proceed to
check if the grammar is context-sensitive by ensuring that each production is of the form αBβ → αγβ, where B is a
non-terminal and α, β, and γ are strings of terminals and non-terminals. If the grammar is context-sensitive, we
return "Context-sensitive grammar". If not, the grammar is unrestricted, and we return "Unrestricted grammar".

```java
Set<String> VN = new HashSet<>(Arrays.asList("S", "B", "C"));
        Set<String> VT = new HashSet<>(Arrays.asList("a", "b", "c"));
        Map<String, List<String>> P = new HashMap<>();
        P.put("S", List.of("aB"));
        P.put("B", Arrays.asList("aC", "bB"));
        P.put("C", Arrays.asList("bB", "c", "aS"));
        String classification = GrammarClassifier.classifyGrammar(VN, VT, P);
        System.out.println(classification);
```

Also I write function of conversion of a finite automaton to a regular grammar, that Determine whether your FA is deterministic or non-deterministic
```java
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
```

And at the last I implement some functionality that would convert an NDFA to a DFA, but my finite Automata is deterministic that's why I cannot show something changing

```java
private Set<String> states;
private Set<String> acceptingStates;
private String startState;
private Map<String, Map<Character, Set<String>>>transitions;

public CheckIdIsGood(Set<String> states,Set<String> acceptingStates,String startState,
        Map<String, Map<Character, Set<String>>>transitions){
        this.states=states;
        this.acceptingStates=acceptingStates;
        this.startState=startState;
        this.transitions=transitions;
        }

public boolean accepts(String input){
        String currentState=startState;
        for(char c:input.toCharArray()){
        if(!transitions.containsKey(currentState)||!transitions.get(currentState).containsKey(c)){
        return false;
        }
        currentState=transitions.get(currentState).get(c).iterator().next();
        }
        return acceptingStates.contains(currentState);
        }
```

## Conclusions

I learned what an automaton is and what it can be used for, how to implement a function that could classify the grammar based on Chomsky hierarchy. Also how
to implement conversion of a finite automaton to a regular grammar, implement conversion of a finite automaton to a regular grammar and determine whether a FA is deterministic or non-deterministic. 
I created some functionality that would convert an NDFA to a DFA, but as my FA was deterministic, I had no possibility to check it.

## References

- [geeksforgeeks.org](https://www.geeksforgeeks.org/introduction-of-finite-automata/)