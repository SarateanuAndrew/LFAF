package lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final String[] TOKEN_NAMES = {"NUMBER", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "LPAREN", "RPAREN"};
    private static final Pattern[] TOKEN_PATTERNS = {
            Pattern.compile("\\d+"), // Matches one or more digits
            Pattern.compile("\\+"),  // Matches the plus sign
            Pattern.compile("-"),   // Matches the minus sign
            Pattern.compile("\\*"), // Matches the multiply sign
            Pattern.compile("/"),   // Matches the divide sign
            Pattern.compile("\\("), // Matches the left parenthesis
            Pattern.compile("\\)")  // Matches the right parenthesis
    };

    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> lex() throws LexicalException {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            boolean matchFound = false;
            for (int i = 0; i < TOKEN_PATTERNS.length; i++) {
                Matcher matcher = TOKEN_PATTERNS[i].matcher(input.substring(pos));
                if (matcher.lookingAt()) {
                    String lexeme = matcher.group();
                    pos += lexeme.length();
                    tokens.add(new Token(TOKEN_NAMES[i], lexeme));
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                throw new LexicalException("Invalid character at position " + pos);
            }
        }
        return tokens;
    }
}

