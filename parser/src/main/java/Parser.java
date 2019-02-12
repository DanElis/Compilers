package main.java;

import java.io.IOException;
import java.text.ParseException;

public class Parser {
    private Lexer lexer;
    private Lexeme cur;

    public Parser(Lexer lexer) throws IOException, ParseException {
        this.lexer = lexer;
        this.cur = lexer.nextLexeme();
    }

    public long parseExpr() throws IOException, ParseException {
        long temp = parseTerm();
        while (cur.type == Lexeme.Type.PLUS || cur.type == Lexeme.Type.MINUS) {
            Lexeme.Type type = cur.type;
            cur = lexer.nextLexeme();
            if (type == Lexeme.Type.PLUS) {
                temp += parseTerm();
            } else {
                temp -= parseTerm();
            }
        }
        return temp;
    }

    public long parseTerm() throws IOException, ParseException{
        long temp = parseFlat();
        while (cur.type == Lexeme.Type.MULT || cur.type == Lexeme.Type.DIV) {
            Lexeme.Type type = cur.type;
            cur = lexer.nextLexeme();
            if (type == Lexeme.Type.MULT) {
                temp *= parseFlat();
            } else {
                temp /= parseFlat();
            }
        }
        return temp;
    }

    public long parseFlat() throws ParseException, IOException {
        long temp = parsePower();
        if(cur.type == Lexeme.Type.POW){
            cur = lexer.nextLexeme();
            temp = (long) Math.pow(temp, parseFlat());
        }
        return temp;
    }

    public long parsePower() throws IOException, ParseException {
        if(cur.type == Lexeme.Type.MINUS){
            cur = lexer.nextLexeme();
            return -parseAtom();
        } else {
            return parseAtom();
        }
    }

    public long parseAtom() throws IOException, ParseException {
        long temp;
        switch (cur.type) {
            case NUMBER:
                temp = Long.parseLong(cur.value);
                cur = lexer.nextLexeme();
                break;
            case OPEN:
                cur = lexer.nextLexeme();
                temp = parseExpr();
                if(cur.type != Lexeme.Type.CLOSE){
                    throw new ParseException("Unclosed ()", 0);
                }
                cur = lexer.nextLexeme();
                break;
            default:
                throw new ParseException("Unexpected", 0);
        }
        return temp;
    }

    public long calculate() throws IOException, ParseException {
        long temp = parseExpr();
        if(cur.type != Lexeme.Type.EOF){
            throw new ParseException("Unclosed", 0);
        }
        return temp;
    }
}
