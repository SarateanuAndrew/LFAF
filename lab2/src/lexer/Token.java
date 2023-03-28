package lexer;

public class Token {
    private final String name;
    private final String lexeme;

    public Token(String name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    public String getName() {
        return name;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String toString() {
        return String.format("%s(%s)", name, lexeme);
    }
}
