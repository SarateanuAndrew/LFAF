# LFAF

## Student: Sarateanu Andrei

----

## Theory

A regular grammar is a type of formal grammar used to generate regular languages, which are a subset of formal languages. It consists of a set of production rules that define how to generate strings of symbols. The rules in a regular grammar are of the form A -> aB or A -> a, where A and B are non-terminal symbols, a is a terminal symbol, and -> denotes a production rule.

Regular grammars are less expressive than other types of grammars, such as context-free grammars, as they can only generate regular languages. However, regular grammars are important in computer science, particularly in the areas of formal language theory and compiler design, as they are used to define and recognize regular expressions, which are widely used in pattern matching and text processing applications.

A finite automaton, also known as a finite state machine, is a mathematical model used to recognize and process regular languages. It consists of a set of states, transitions between those states, a start state, and one or more final states. It operates by consuming input symbols and transitioning between states based on those symbols until it reaches an accepting state or a non-accepting state. It is used in various areas of computer science, such as compiler design, natural language processing, and digital circuit design.

## Objectives

1) Understand what a language is and what it needs to have in order to be considered a formal one.

2) Provide the initial setup for the evolving project that you will work on during this semester. I said project because usually at lab works, I encourage/impose students to treat all the labs like stages of development of a whole project. Basically you need to do the following:
    - Create a local && remote repository of a VCS hosting service (let us all use Github to avoid unnecessary headaches);
    - Choose a programming language, and my suggestion would be to choose one that supports all the main paradigms;
    - Create a separate folder where you will be keeping the report. This semester I wish I won't see reports alongside source code files, fingers crossed;

3) According to your variant number (by universal convention it is register ID), get the grammar definition and do the following tasks:
    - Implement a type/class for your grammar;
    - Add one function that would generate 5 valid strings from the language expressed by your given grammar;
    - Implement some functionality that would convert and object of type Grammar to one of type Finite Automaton;
    - For the Finite Automaton, please add a method that checks if an input string can be obtained via the state transition from it;

## Implementation description

I decided to make the laboratory in Java:

```java
private Set<String> vn;
    private Set<String> vt;
    private String s;
    private Map<String, List<String>> p;

    public Grammar(Set<String> vn, Set<String> vt, String s, Map<String, List<String>> p) {
        this.vn = vn;
        this.vt = vt;
        this.s = s;
        this.p = p;
    }
```

...you will be able to modify fields, inside class.

After that I implemented the string generator, where you cannot change grammar:

```java
public static List<String> generateValidStrings(int count) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String string = generateValidString();
            result.add(string);
        }

        return result;
    }
}
```
Also I write function to convert Grammar to Finite Automation

```java
public static FiniteAutomation convert(Grammar g) {
        Set<String> states = new HashSet<>();
        states.add(g.getS());
        for (String vn : g.getVn()) {
            for (String s : g.getP().get(vn)) {
                for (int i = 0; i < s.length(); i++) {
                    if (g.getVt().contains(s.charAt(i) + "")) {
                        states.add(s.substring(0, i) + s.substring(i + 1));
                    }
                }
            }
        }
```

And at the last the function that checks if the string is valid:

```java
private Set<String> states;
    private Set<String> acceptingStates;
    private String startState;
    private Map<String, Map<Character, Set<String>>> transitions;

    public CheckIdIsGood(Set<String> states, Set<String> acceptingStates, String startState,
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
```

## Conclusions

I learned what is a formal language, how to implement the Finite Automata and some functions that it must have. Also how to make the class more flexible, to grammar modification. In another words it is available for any grammar, provided.

## References

- [geeksforgeeks.org](https://www.geeksforgeeks.org/introduction-of-finite-automata/)




# LFAF

## Student: Sarateanu Andrei

----

## Theory

A regular grammar is a type of formal grammar used to generate regular languages, which are a subset of formal languages. It consists of a set of production rules that define how to generate strings of symbols. The rules in a regular grammar are of the form A -> aB or A -> a, where A and B are non-terminal symbols, a is a terminal symbol, and -> denotes a production rule.

Regular grammars are less expressive than other types of grammars, such as context-free grammars, as they can only generate regular languages. However, regular grammars are important in computer science, particularly in the areas of formal language theory and compiler design, as they are used to define and recognize regular expressions, which are widely used in pattern matching and text processing applications.

A finite automaton, also known as a finite state machine, is a mathematical model used to recognize and process regular languages. It consists of a set of states, transitions between those states, a start state, and one or more final states. It operates by consuming input symbols and transitioning between states based on those symbols until it reaches an accepting state or a non-accepting state. It is used in various areas of computer science, such as compiler design, natural language processing, and digital circuit design.

## Objectives

1) Understand what a language is and what it needs to have in order to be considered a formal one.

2) Provide the initial setup for the evolving project that you will work on during this semester. I said project because usually at lab works, I encourage/impose students to treat all the labs like stages of development of a whole project. Basically you need to do the following:
   - Create a local && remote repository of a VCS hosting service (let us all use Github to avoid unnecessary headaches);
   - Choose a programming language, and my suggestion would be to choose one that supports all the main paradigms;
   - Create a separate folder where you will be keeping the report. This semester I wish I won't see reports alongside source code files, fingers crossed;

3) According to your variant number (by universal convention it is register ID), get the grammar definition and do the following tasks:
   - Implement a type/class for your grammar;
   - Add one function that would generate 5 valid strings from the language expressed by your given grammar;
   - Implement some functionality that would convert and object of type Grammar to one of type Finite Automaton;
   - For the Finite Automaton, please add a method that checks if an input string can be obtained via the state transition from it;

## Implementation description

I decided to make the laboratory in Java:

```java
private Set<String> vn;
    private Set<String> vt;
    private String s;
    private Map<String, List<String>> p;

    public Grammar(Set<String> vn, Set<String> vt, String s, Map<String, List<String>> p) {
        this.vn = vn;
        this.vt = vt;
        this.s = s;
        this.p = p;
    }
```

...you will be able to modify fields, inside class.

After that I implemented the string generator, where you cannot change grammar:

```java
public static List<String> generateValidStrings(int count) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String string = generateValidString();
            result.add(string);
        }

        return result;
    }
}
```
Also I write function to convert Grammar to Finite Automation

```java
public static FiniteAutomation convert(Grammar g) {
        Set<String> states = new HashSet<>();
        states.add(g.getS());
        for (String vn : g.getVn()) {
            for (String s : g.getP().get(vn)) {
                for (int i = 0; i < s.length(); i++) {
                    if (g.getVt().contains(s.charAt(i) + "")) {
                        states.add(s.substring(0, i) + s.substring(i + 1));
                    }
                }
            }
        }
```

And at the last the function that checks if the string is valid:

```java
private Set<String> states;
    private Set<String> acceptingStates;
    private String startState;
    private Map<String, Map<Character, Set<String>>> transitions;

    public CheckIdIsGood(Set<String> states, Set<String> acceptingStates, String startState,
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
```

## Conclusions

I learned what is a formal language, how to implement the Finite Automata and some functions that it must have. Also how to make the class more flexible, to grammar modification. In another words it is available for any grammar, provided.

## References

- [geeksforgeeks.org](https://www.geeksforgeeks.org/introduction-of-finite-automata/)