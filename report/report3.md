# LFAF

## Student: Sarateanu Andrei

----

## Overview
&ensp;&ensp;&ensp; The term lexer comes from lexical analysis which, in turn, represents the process of extracting lexical tokens from a string of characters. There are several alternative names for the mechanism called lexer, for example tokenizer or scanner. The lexical analysis is one of the first stages used in a compiler/interpreter when dealing with programming, markup or other types of languages.
&ensp;&ensp;&ensp; The tokens are identified based on some rules of the language and the products that the lexer gives are called lexemes. So basically the lexer is a stream of lexemes. Now in case it is not clear what's the difference between lexemes and tokens, there is a big one. The lexeme is just the byproduct of splitting based on delimiters, for example spaces, but the tokens give names or categories to each lexeme. So the tokens don't retain necessarily the actual value of the lexeme, but rather the type of it and maybe some metadata.


## Objectives:
1. Understand what lexical analysis [1] is.
2. Get familiar with the inner workings of a lexer/scanner/tokenizer.
3. Implement a sample lexer and show how it works.

## Theory

&ensp;&ensp;&ensp; A lexer (short for lexical analyzer) is a tool used in programming to break up a stream of characters into meaningful tokens, which can then be parsed and analyzed by a compiler or interpreter. 
In Java, we can implement a simple lexer using regular expressions to match different patterns of characters.

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

Finally, I define a main method that demonstrates the lexer in action. I create an example input string, 
tokenize it using the tokenize method, and print out each resulting token.

```java
//Check Lexer
        System.out.println("Check Lexer");
                ArrayList<Token> tokens = tokenize("x+42*y-z/2");
        for (Token token : tokens) {
        System.out.println(token);
        }
```


## Conclusions

I learned what lexical analysis is, I got familiar with the inner workings of a lexer/scanner/tokenizer and created Token and an enum 
TokenType to specify the symbols. The Implementation is written in the Lexer class. Finally, I have applied the function with the 
string: "x+42*y-z/2".

## References:
[1] [A sample of a lexer implementation](https://llvm.org/docs/tutorial/MyFirstLanguageFrontend/LangImpl01.html)

[2] [Lexical analysis](https://en.wikipedia.org/wiki/Lexical_analysis)