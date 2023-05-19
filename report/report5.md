# Topic: Parser & Building an Abstract Syntax Tree

### Course: Formal Languages & Finite Automata
### Author: Sarateanu Andrei-Cristian

----

## Overview
&ensp;&ensp;&ensp; The process of gathering syntactical meaning or doing a syntactical analysis over some text can also be called parsing. It usually results in a parse tree which can also contain semantic information that could be used in subsequent stages of compilation, for example.

&ensp;&ensp;&ensp; Similarly to a parse tree, in order to represent the structure of an input text one could create an Abstract Syntax Tree (AST). This is a data structure that is organized hierarchically in abstraction layers that represent the constructs or entities that form up the initial text. These can come in handy also in the analysis of programs or some processes involved in compilation.


## Objectives:
1. Get familiar with parsing, what it is and how it can be programmed [1].
2. Get familiar with the concept of AST [2].
3. In addition to what has been done in the 3rd lab work do the following:
    1. In case you didn't have a type that denotes the possible types of tokens you need to:
        1. Have a type __*TokenType*__ (like an enum) that can be used in the lexical analysis to categorize the tokens.
        2. Please use regular expressions to identify the type of the token.
    2. Implement the necessary data structures for an AST that could be used for the text you have processed in the 3rd lab work.
    3. Implement a simple parser program that could extract the syntactic information from the input text.


## Theory

&ensp;&ensp;&ensp; In computer technology, a parser is a program that's usually part of a compiler. It receives input in the form of sequential source program instructions, interactive online commands, markup tags or some other defined interface.

Parsers break the input they get into parts such as the nouns (objects), verbs (methods), and their attributes or options. These are then managed by other programming, such as other components in a compiler. A parser may also check to ensure that all the necessary input has been provided.

## Implementation of Lexer with Token Type
First, I define three regular expressions using the Pattern class: NUMBER_PATTERN matches one or more digits,
IDENTIFIER_PATTERN matches one or more letters, and OPERATOR_PATTERN matches one of the four basic arithmetic operators.

```java
private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-zA-Z]+");
private static final Pattern OPERATOR_PATTERN = Pattern.compile("[+\\-*/]");
```

Next, I define a tokenize method that takes an input string and returns an ArrayList of Token objects.

```java
 public static ArrayList<Token> tokenize(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
```

I use a while loop to repeatedly match the input against each of the three patterns in turn,
adding the corresponding token to the output list and removing the matched characters from the input string.

```java
while (!input.isEmpty()) {
Matcher numberMatcher = NUMBER_PATTERN.matcher(input);
Matcher identifierMatcher = IDENTIFIER_PATTERN.matcher(input);
Matcher operatorMatcher = OPERATOR_PATTERN.matcher(input);
```

If none of the patterns match, it throw an exception indicating that the input contains an unrecognized character.

```java
else {
        throw new RuntimeException("Unrecognized token: " + input.charAt(0));
        }
```

I define a main method that demonstrates the lexer in action. I create an example input string,
tokenize it using the tokenize method, and print out each resulting token.

```java
        System.out.println("Check Lexer");
                ArrayList<Token> tokens = tokenize("x+42*y-z/2");
        for (Token token : tokens) {
        System.out.println(token);
        }
```

TokenType is established in an Enum with the same name it finds weather we have to deal with an Number, Operator or Identifier, that's why we have to introduce an arithmetic example:
```java
public enum TokenType {
    NUMBER,
    IDENTIFIER,
    OPERATOR
}
```

## Parser Implementation
In order to create the parser I need to download the Stanford CoreNLP library and add it to my project's classpath. I download it from the official website: https://stanfordnlp.github.io/CoreNLP/
This program uses the Stanford CoreNLP library to parse the input sentence, tokenize it into words, and generate a parse tree. It then binarizes the parse tree and extracts the syntactic dependencies from the binarized tree. Finally, it prints the extracted dependencies.

```java
System.out.println("Parser: ");

        // Path to the pretrained English PCFG parser model
        String modelPath = "englishPCFG.ser.gz";

        // Text to parse
        String text = "x+12+42*y-z/2";

        // Initialize the parser
        LexicalizedParser parser = LexicalizedParser.loadModel(modelPath);
        TreeBinarizer binarizer = TreeBinarizer.simpleTreeBinarizer(parser.getTLPParams().headFinder(), parser.treebankLanguagePack());

        // Tokenize the text
        String[] words = text.split(" ");

        // Parse the sentence
        Tree parseTree = parser.apply(SentenceUtils.toWordList(words));
        Tree binarizedTree = binarizer.transformTree(parseTree);

        // Extract syntactic information
        TreebankLanguagePack tlp = parser.treebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(binarizedTree);
        Collection<TypedDependency> dependencies = gs.typedDependenciesCCprocessed();

        // Print the dependencies
        for (TypedDependency dependency : dependencies) {
            System.out.println(dependency);
        }
```

## Conclusions

I learned what parser analysis is, I got familiar with the inner workings of a parser and created Parser and an enum
TokenType to specify the symbols. To implement Parser I need to introduce a jar file from Stanford CoreNLP library. Finally, I have applied the parser with the
string: "x+42*y-z/2".

## References:
[1] [Parsing Wiki](https://en.wikipedia.org/wiki/Parsing)

[2] [Abstract Syntax Tree Wiki](https://en.wikipedia.org/wiki/Abstract_syntax_tree)
 