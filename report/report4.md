# Topic: Chomsky Normal Form

### Course: Formal Languages & Finite Automata
### Author: Sarateanu Andrei-Cristian

---


## Objectives:
1. Learn about Chomsky Normal Form (CNF) [1].
2. Get familiar with the approaches of normalizing a grammar.
3. Implement a method for normalizing an input grammar by the rules of CNF.
    1. The implementation needs to be encapsulated in a method with an appropriate signature (also ideally in an appropriate class/type).
    2. The implemented functionality needs executed and tested.
    3. A BONUS point will be given for the student who will have unit tests that validate the functionality of the project.
    4. Also, another BONUS point would be given if the student will make the aforementioned function to accept any grammar, not only the one from the student's variant.

## Theory
## CNF stands for Chomsky normal form. A CFG(context free grammar) is in CNF(Chomsky normal form) if all production rules satisfy one of the following conditions:
1) Start symbol generating ε. For example, A → ε.
2) A non-terminal generating two non-terminals. For example, S → AB.
3) A non-terminal generating a terminal. For example, S → a.

Every grammar in Chomsky normal form is context-free, and conversely, every context-free grammar can be transformed into an equivalent one which is in Chomsky normal form and has a size no larger than the square of the original grammar's size.

I decided to create a separate Class "Chomsky" in order to add functions to eliminate espilon factor:

```java
public void eliminateEpsilonProductions() {
        Set<String> nullables = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : productions.entrySet()) {
        if (entry.getValue().contains("ε")) {
        nullables.add(entry.getKey());
        }
        }
```

Next, I created the function to eliminate the unit productions.

```java
public void eliminateUnitProductions() {
        for (String symbol : this.productions.keySet()) {
        List<String> unit_productions = new ArrayList<>();
        for (String prod : this.productions.get(symbol)) {
        if (prod.length() == 1 && Character.isUpperCase(prod.charAt(0))) {
        unit_productions.add(prod);
        }
        }
```

If we want to realize the conversion we have to create a function that will eliminate the inaccessible symbols:

```java
public void eliminateInaccessibleSymbols() {
        Set<String> visited = new HashSet<>();
        visit(this.startSymbol, visited);

        List<String> newNonTerminal = new ArrayList<>();
        Map<String, List<String>> newProductions = new HashMap<>();
        for (String nt : this.nonTerminal) {
        if (visited.contains(nt)) {
        newNonTerminal.add(nt);
        newProductions.put(nt, this.productions.get(nt));
        }
        }

        this.nonTerminal = newNonTerminal;
        this.productions = newProductions;
        }
```

At the end I created the function for realizing the final transformation in Chomsky Normal Form.
Finally, I define a main method that demonstrates the transformation in action. Also, I created the tests for parts of functions to demonstrate that transformation is correct 
I checked transformations on paper and that was true.

```java
@Test
public void testEliminateEpsilonProductions() {

        chomsky.eliminateEpsilonProductions();

        Map<String, List<String>> expected = new HashMap<>() {{
        put("S", List.of("bA", "bAC", "B"));
        put("A", List.of("a", "aS", "bab", "bCaCb"));
        put("B", List.of("A", "AC", "bS", "aAa"));
        put("C", List.of("AB"));
        put("E", List.of("BA"));
        }};

        assertEquals(expected, chomsky.getProductions());
        }
```


## Conclusions

I learned how to normalize a context-free grammar into a Chomsky normal Form. My program works for every grammar, so you can try to put another one to check it. Also, I learned how to test my code and create assets.

## References:
[1] [Chomsky Normal Form Wiki](https://en.wikipedia.org/wiki/Chomsky_normal_form)