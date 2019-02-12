package main.java;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

public class Lexer {
    private Reader reader;
    private int cur;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        cur = reader.read();
    }

    public Lexeme nextLexeme() throws IOException, ParseException {
        while (Character.isSpaceChar(cur)) { // Skip spaces
            cur = reader.read();
        }
        Lexeme l;
        switch (cur) {
            case '+':
                l = new Lexeme(Lexeme.Type.PLUS, "+");
                cur = reader.read();
                break;
            case '-':
                l = new Lexeme(Lexeme.Type.MINUS, "-");
                cur = reader.read();
                break;
            case '*':
                l = new Lexeme(Lexeme.Type.MULT, "*");
                cur = reader.read();
                break;
            case '/':
                l = new Lexeme(Lexeme.Type.DIV, "/");
                cur = reader.read();
                break;
            case '^':
                l = new Lexeme(Lexeme.Type.POW, "^");
                cur = reader.read();
                break;
            case '(':
                l = new Lexeme(Lexeme.Type.OPEN, "(");
                cur = reader.read();
                break;
            case ')':
                l = new Lexeme(Lexeme.Type.CLOSE, ")");
                cur = reader.read();
                break;
            case -1:
                l = new Lexeme(Lexeme.Type.EOF, "");
                cur = reader.read();
                break;
            default:
                if (!Character.isDigit(cur)) {
                    throw new ParseException("Unknown character ", cur);
                }
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(cur)) {
                    sb.append((char)cur);
                    cur = reader.read();
                }
                l = new Lexeme(Lexeme.Type.NUMBER, sb.toString());
                break;
        }
        return l;
    }
}
