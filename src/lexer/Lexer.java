package lexer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("[+\\-*/]");

    public static ArrayList<Token> tokenize(String input) {
        ArrayList<Token> tokens = new ArrayList<>();

        while (!input.isEmpty()) {
            Matcher numberMatcher = NUMBER_PATTERN.matcher(input);
            Matcher identifierMatcher = IDENTIFIER_PATTERN.matcher(input);
            Matcher operatorMatcher = OPERATOR_PATTERN.matcher(input);

            if (numberMatcher.lookingAt()) {
                String value = numberMatcher.group();
                tokens.add(new Token(TokenType.NUMBER, value));
                input = input.substring(value.length());
            } else if (identifierMatcher.lookingAt()) {
                String value = identifierMatcher.group();
                tokens.add(new Token(TokenType.IDENTIFIER, value));
                input = input.substring(value.length());
            } else if (operatorMatcher.lookingAt()) {
                String value = operatorMatcher.group();
                tokens.add(new Token(TokenType.OPERATOR, value));
                input = input.substring(value.length());
            } else {
                throw new RuntimeException("Unrecognized token: " + input.charAt(0));
            }
        }

        return tokens;
    }
}

